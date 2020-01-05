package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.banner.BannerModel
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomResponse
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseQualification.CourseQualificationModel
import com.syahputrareno975.ayolesapp.model.courseQualification.OneCourseQualificationRequest
import com.syahputrareno975.ayolesapp.model.courseQualification.OneCourseQualificationResponse
import com.syahputrareno975.ayolesapp.model.student.StudentModel
import com.syahputrareno975.ayolesapp.ui.activity.material_classroom.MaterialClassRoomActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterBanner
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterImageDetailCourse
import com.syahputrareno975.ayolesapp.util.EmptyUUID
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.activity_detail_course.*
import java.util.*
import javax.inject.Inject

class DetailCourseActivity : AppCompatActivity(),DetailCourseActivityContract.View {

    @Inject
    lateinit var presenter: DetailCourseActivityContract.Presenter

    companion object {
        private val limit_load : Int = 3
    }

    lateinit var context: Context

    lateinit var adapterImageDetailCourse : AdapterImageDetailCourse
    lateinit var courseData: CourseModel

    val reqAllCourseDetails = AllCourseDetailRequest()

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

        reqAllCourseDetails.Limit = limit_load
        reqAllCourseDetails.CourseId = courseData.Id

        course_qualification_textview.text = CourseQualificationModel().toString(context)

        back_imageview.setOnClickListener {
            finish()
        }

        add_to_class_button.setOnClickListener {
            presenter.addClassRoom(AddClassRoomRequest(courseData.Id,studentSession.Id))
        }


        course_name_textview.text = courseData.CourseName
        setImageCourse()

        //fake_toolbar.background.alpha = 125

        presenter.getOneClassRoomById(OneClassByIdRoomRequest(courseData.Id,studentSession.Id))
        class_is_taken.visibility = View.GONE
        go_to_class_button.visibility = View.GONE
        add_to_class_button.visibility = View.GONE
    }

    fun setImageCourse(){
        adapterImageDetailCourse = AdapterImageDetailCourse(context,courseData.CourseDetails)
        image_course_recycleview.adapter = adapterImageDetailCourse
        image_course_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(image_course_recycleview)

        setDetailsContentBaseOnSelectedImage()

        courseData.CourseDetails.clear()
        presenter.getAllCourseDetails(reqAllCourseDetails)
    }

    fun setDetailsContentBaseOnSelectedImage(){
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
        add_to_class_button.visibility = View.GONE
        class_is_taken.visibility = View.VISIBLE
        go_to_class_button.visibility = View.VISIBLE
        presenter.getOneClassRoomById(OneClassByIdRoomRequest(courseData.Id,studentSession.Id))
    }

    override fun onGetOneClassRoomById(s: OneClassByIdRoomResponse) {
        add_to_class_button.visibility = if (s.Data.ClassroomDetailById.Course.Id == EmptyUUID.EmptyUUID) View.VISIBLE else View.GONE
        class_is_taken.visibility = if (s.Data.ClassroomDetailById.Course.Id == EmptyUUID.EmptyUUID) View.GONE else  View.VISIBLE
        go_to_class_button.visibility = if (s.Data.ClassroomDetailById.Course.Id == EmptyUUID.EmptyUUID) View.GONE else  View.VISIBLE

        go_to_class_button.setOnClickListener {
            val intent = Intent(context, MaterialClassRoomActivity::class.java)
            intent.putExtra("data",s.Data.ClassroomDetailById)
            startActivity(intent)
            finish()
        }

    }

    override fun onGetAllCourseDetails(r: AllCourseDetailResponse) {
        courseData.CourseDetails.addAll(r.Data.CourseDetailList)
        adapterImageDetailCourse.notifyDataSetChanged()
        setDetailsContentBaseOnSelectedImage()
        presenter.getOneCourseQualification(OneCourseQualificationRequest("",courseData.Id))
    }

    override fun onGetOneCourseQualification(s: OneCourseQualificationResponse) {
        course_qualification_textview.text = s.Data.CourseQualificationDetail.toString(context)
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
