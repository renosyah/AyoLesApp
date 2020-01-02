package com.syahputrareno975.ayolesapp.ui.activity.exam_classroom

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
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamRequest
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamResponse
import com.syahputrareno975.ayolesapp.model.courseExam.CourseExamModel
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

    val reqAllExam : AllCourseExamRequest = AllCourseExamRequest()

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

        title_exam_textview.text = "${classRoomModel.Course.CourseName}'s Exam"
        finish_exam_button.visibility = View.GONE


        exam_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllExam.Offset += limit_load
                presenter.getAllExam(reqAllExam)
            }
        })

        requestAllExam()
    }

    fun requestAllExam(){
        adapterExam = AdapterExam(context,listExam,{courseExamModel, i ->
            listExam[i].IsSubmited = true
            adapterExam.notifyDataSetChanged()

        },{courseExamAnswerModel,posExamp, posAnswer ->
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
    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listExam.isEmpty() || forceShow) View.VISIBLE else View.GONE
        exam_recycleview.visibility = if (listExam.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }
    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        checkNoResultFound(true)
    }

    override fun onGetAllExam(s: AllCourseExamResponse) {
        listExam.addAll(s.Data.CourseExamList)
        finish_exam_button.visibility = if (s.Data.CourseExamList.isEmpty()) View.VISIBLE else View.GONE
        adapterExam.notifyDataSetChanged()
        checkNoResultFound(false)
    }

    override fun onBackPressed() {

        AlertDialog.Builder(context)
                .setTitle("Start Exam")
                .setMessage("Are you sure want to finish and exit ${classRoomModel.Course.CourseName}'s exam?")
                .setPositiveButton("Yes") { dialog, which ->
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
