package com.syahputrareno975.ayolesapp.ui.activity.exam_classroom

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        title_exam_textview.text = "${classRoomModel.Course.CourseName}'s Exam"
        not_found.visibility = View.GONE
        finish_exam_button.visibility = View.GONE
        finish_exam_button.setOnClickListener {

            // submit exam
            // for final score
            Toast.makeText(context,if (isAllExamSubmited()) "Exam finish" else "please answer all exam",Toast.LENGTH_SHORT).show()
            if (!isAllExamSubmited()){
                return@setOnClickListener
            }
            presenter.addClassRoomCertificate(AddClassRoomCertificateRequest(classRoomModel.Id))
        }

        exam_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllExam.Offset += limit_load
                reqAllExamLastProgress.Offset += limit_load
                presenter.getAllExam(reqAllExam)
            }
        })

        requestAllExam()
    }

    fun requestAllExam(){
        adapterExam = AdapterExam(context,listExam,{courseExamModel, i ->
            if (!listExam.get(i).isAnswered()) {
                Toast.makeText(context,"Please choose answer!",Toast.LENGTH_SHORT).show()
                return@AdapterExam
            }
            if (courseExamModel.getAnswer() != null){
                val answer = courseExamModel.getAnswer()
                presenter.addClassRoomExamProgress(AddClassRoomExamProgressRequest(classRoomModel.Id,courseExamModel.Id, answer!!.Id),i)
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
        presenter.getAllExam(reqAllExam)
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

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        checkNoResultFound(true)
        Toast.makeText(context,error,Toast.LENGTH_LONG).show()
    }

    override fun onGetAllExam(s: AllCourseExamResponse) {
        listExam.addAll(s.Data.CourseExamList)
        finish_exam_button.visibility = if (s.Data.CourseExamList.isEmpty() && isAllExamSubmited()) View.VISIBLE else View.GONE
        adapterExam.notifyDataSetChanged()
        checkNoResultFound(false)
        presenter.getAllClassRoomExamProgress(reqAllExamLastProgress)
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
                .setTitle("Quit Exam")
                .setMessage("Are you sure want to finish and exit ${classRoomModel.Course.CourseName}'s exam?")
                .setPositiveButton("Yes") { dialog, which ->
                    setResult(Activity.RESULT_OK)
                    finish()
                    dialog.dismiss()

                }.setNegativeButton("No"){dialog, which ->
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
