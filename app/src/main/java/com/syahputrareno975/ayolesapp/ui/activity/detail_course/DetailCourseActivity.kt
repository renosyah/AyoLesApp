package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.banner.BannerModel
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterBanner
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterImageDetailCourse
import kotlinx.android.synthetic.main.activity_detail_course.*
import javax.inject.Inject

class DetailCourseActivity : AppCompatActivity(),DetailCourseActivityContract.View {

    @Inject
    lateinit var presenter: DetailCourseActivityContract.Presenter

    lateinit var context: Context
    lateinit var courseData: CourseModel

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

        back_imageview.setOnClickListener {
            finish()
        }

        course_name_textview.text = courseData.CourseName
        setImageCourse()

        fake_toolbar.background.alpha = 125
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
