package com.syahputrareno975.ayolesapp.ui.activity.exam_classroom

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.*
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamRequest
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamResponse
import com.syahputrareno975.ayolesapp.model.courseExam.CourseExamModel
import com.syahputrareno975.ayolesapp.ui.activity.exam_result.ExamResultActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterExam
import kotlinx.android.synthetic.main.activity_exam_class_room.*
import javax.inject.Inject


class ExamClassRoomActivity : AppCompatActivity(),ExamClassRoomActivityContract.View {

    @Inject
    lateinit var presenter: ExamClassRoomActivityContract.Presenter
    companion object {
        val limit_load = 5
    }

    lateinit var context: Context

    lateinit var classRoomModel : ClassRoomModel

    lateinit var adapterExam: AdapterExam
    val listExam : ArrayList<CourseExamModel> = ArrayList()
    val listExamProgress : ArrayList<ClassRoomExamProgressModel> = ArrayList()

    val reqAllExam : AllCourseExamRequest = AllCourseExamRequest()
    val reqAllExamLastProgress : AllClassRoomExamProgressRequest = AllClassRoomExamProgressRequest()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_class_room)
        initWidget()
    }

    @SuppressLint("SetTextI18n")
    fun initWidget(){
        this.context = this@ExamClassRoomActivity

        classRoomModel = intent.getSerializableExtra("data") as ClassRoomModel

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        reqAllExam.Offset = 0
        reqAllExam.Limit = limit_load
        reqAllExam.CourseId = classRoomModel.Course.Id

        reqAllExamLastProgress.Offset = 0
        reqAllExamLastProgress.Limit = limit_load
        reqAllExamLastProgress.ClassroomId = classRoomModel.Id

        title_exam_textview.text = " ${getString(R.string.exam_title)} ${classRoomModel.Course.CourseName}"
        not_found.visibility = View.GONE
        finish_exam_button.visibility = View.GONE

        finish_exam_button.setOnClickListener {

            // submit exam
            // for final score
            Toast.makeText(context,if (isAllExamSubmited()) getString(R.string.exam_finish) else getString(R.string.please_answer_exam),Toast.LENGTH_SHORT).show()
            if (!isAllExamSubmited()){
                return@setOnClickListener
            }
            presenter.addClassRoomCertificate(AddClassRoomCertificateRequest(classRoomModel.Id),false)
        }

        exam_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllExam.Offset += limit_load
                reqAllExamLastProgress.Offset += limit_load
                presenter.getAllExam(reqAllExam,false)
            }
        })

        requestAllExam()
    }

    fun requestAllExam(){
        adapterExam = AdapterExam(context,listExam,{courseExamModel, i ->
            if (!listExam.get(i).isAnswered()) {
                Toast.makeText(context, getString(R.string.please_choose_answer),Toast.LENGTH_SHORT).show()
                return@AdapterExam
            }
            if (courseExamModel.getAnswer() != null){
                val answer = courseExamModel.getAnswer()
                presenter.addClassRoomExamProgress(AddClassRoomExamProgressRequest(classRoomModel.Id,courseExamModel.Id, answer!!.Id),i,false)
            }

        },{courseExamAnswerModel,posExamp, posAnswer ->
            if (listExam[posExamp].IsSubmited){
                return@AdapterExam
            }
            for (p in listExam[posExamp].Answers){
                p.IsSelected = false
            }
            listExam[posExamp].Answers[posAnswer].IsSelected = true
            adapterExam.notifyDataSetChanged()
        })
        exam_recycleview.adapter = adapterExam
        exam_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        presenter.getAllExam(reqAllExam,true)
    }

    fun isAllExamSubmited() : Boolean {
        for (i in listExam){
            if (!i.IsSubmited){
                return false
            }
        }
        return true
    }

    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listExam.isEmpty() || forceShow) View.VISIBLE else View.GONE
        exam_recycleview.visibility = if (listExam.isEmpty() || forceShow) View.GONE else View.VISIBLE
        finish_exam_button.visibility = if (listExam.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }

    fun validateWithLastProgress(){
        for (i in listExamProgress){
            for (o in listExam){
                if (o.Id == i.CourseExamId){
                    o.IsSubmited = true
                    for (p in o.Answers){
                        if (p.Id == i.CourseExamAnswerId) {
                            p.IsSelected = true
                        }
                    }
                }
            }
        }
        adapterExam.notifyDataSetChanged()
    }

    override fun showProgressOnGetAllExam(show: Boolean) {
        loading_data_exam.visibility = if (show) View.VISIBLE else View.GONE
        exam_recycleview.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnGetAllExam(error: String) {
        loading_data_exam.visibility = View.GONE
        checkNoResultFound(true)
    }

    override fun showProgressOnAddClassRoomExamProgress(show: Boolean) {

    }

    override fun showErrorOnAddClassRoomExamProgress(error: String) {

    }

    override fun showProgressOnGetAllClassRoomExamProgress(show: Boolean) {

    }

    override fun showErrorOnGetAllClassRoomExamProgress(error: String) {

    }

    override fun showProgressOnAddClassRoomCertificate(show: Boolean) {

    }

    override fun showErrorOnAddClassRoomCertificate(error: String) {

    }


    override fun onGetAllExam(s: AllCourseExamResponse) {
        listExam.addAll(s.Data.CourseExamList)
        finish_exam_button.visibility = if (s.Data.CourseExamList.isEmpty() && isAllExamSubmited()) View.VISIBLE else View.GONE
        adapterExam.notifyDataSetChanged()
        checkNoResultFound(false)
        presenter.getAllClassRoomExamProgress(reqAllExamLastProgress,false)
    }

    override fun onAddClassRoomExamProgress(s: AddClassRoomExamProgressResponse,posExampSubmited : Int) {
        listExam[posExampSubmited].IsSubmited = true
        adapterExam.notifyDataSetChanged()
    }

    override fun onGetAllClassRoomExamProgress(s: AllClassRoomExamProgressResponse) {
        listExamProgress.addAll(s.Data.ClassRoomExamProgressList)
        validateWithLastProgress()
    }
    override fun onAddClassRoomCertificate(r: AddClassRoomCertificateResponse) {
        // go to exam result
        // activity
        val intent = Intent(context,ExamResultActivity::class.java)
        intent.putExtra("data",classRoomModel)
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {

        if (isAllExamSubmited()){
            super.onBackPressed()
            return
        }

        AlertDialog.Builder(context)
                .setTitle(getString(R.string.quit_exam))
                .setMessage(getString(R.string.quit_exam_message))
                .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                    setResult(Activity.RESULT_OK)
                    finish()
                    dialog.dismiss()

                }.setNegativeButton(getString(R.string.no)){dialog, which ->
                    dialog.dismiss()

                }.setCancelable(false)
                .create()
                .show()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun injectDependency(){
        val listcomponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        listcomponent.inject(this)
    }


}
