package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.banner.BannerModel
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomResponse
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.model.student.StudentModel
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterBanner
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterImageDetailCourse
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.activity_detail_course.*
import java.util.*
import javax.inject.Inject

class DetailCourseActivity : AppCompatActivity(),DetailCourseActivityContract.View {

    @Inject
    lateinit var presenter: DetailCourseActivityContract.Presenter

    lateinit var context: Context
    lateinit var courseData: CourseModel

    lateinit var studentSession : StudentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        initWidget()
    }

    fun initWidget(){
        this.context = this@DetailCourseActivity

        courseData = intent.getSerializableExtra("data") as CourseModel

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()


        if (SerializableSave(context, SerializableSave.userDataFileSessionName).load() != null){
            studentSession = SerializableSave(context, SerializableSave.userDataFileSessionName).load() as StudentModel
        }

        back_imageview.setOnClickListener {
            finish()
        }

        add_to_class_button.setOnClickListener {
            presenter.addClassRoom(AddClassRoomRequest(courseData.Id,studentSession.Id))
        }

        course_name_textview.text = courseData.CourseName
        setImageCourse()

        fake_toolbar.background.alpha = 125

        presenter.getOneClassRoomById(OneClassByIdRoomRequest(courseData.Id,studentSession.Id))
        class_is_taken.visibility = View.GONE
        add_to_class_button.visibility = View.GONE
    }

    fun setImageCourse(){
        image_course_recycleview.adapter = AdapterImageDetailCourse(context,courseData.CourseDetails)
        image_course_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(image_course_recycleview)

        if (courseData.CourseDetails.isNotEmpty()){
            image_course_recycleview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    setTextCourse((recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
                }
            })
            setTextCourse(0)
        }

    }

    fun setTextCourse(pos : Int){
        overview_textview.text = courseData.CourseDetails.get(pos).OverviewText
        description_textview.text = courseData.CourseDetails.get(pos).DescriptionText
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {

    }

    override fun onAddClassRoom(s: AddClassRoomResponse) {
        Toast.makeText(context,"${s.Data.ClassRoomRegister.Course.CourseName} is added to your classroom",Toast.LENGTH_SHORT).show()
        add_to_class_button.visibility = View.GONE
        class_is_taken.visibility = View.VISIBLE
    }

    override fun onGetOneClassRoomById(s: OneClassByIdRoomResponse) {
        add_to_class_button.visibility = if (s.Data.ClassroomDetailById.Course.CourseName.isEmpty()) View.VISIBLE else View.GONE
        class_is_taken.visibility = if (s.Data.ClassroomDetailById.Course.CourseName.isEmpty()) View.GONE else  View.VISIBLE
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
