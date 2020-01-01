package com.syahputrareno975.ayolesapp.ui.activity.material_classroom

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.ClassRoomProgressModel
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.CourseMaterialModel
import com.syahputrareno975.ayolesapp.ui.activity.material_detail.MaterialDetailClassRoomActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterImageDetailCourse
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterMaterialClassRoom
import kotlinx.android.synthetic.main.activity_material_classroom.*
import javax.inject.Inject

class MaterialClassRoomActivity : AppCompatActivity(),MaterialClassRoomActivityContract.View {

    @Inject
    lateinit var presenter: MaterialClassRoomActivityContract.Presenter

    lateinit var context: Context
    lateinit var classRoomModel : ClassRoomModel

    companion object {
        private val limit_load : Int = 3
    }

    lateinit var adapterMaterialClassRoom : AdapterMaterialClassRoom
    val listMaterialClassRoom : ArrayList<CourseMaterialModel> = ArrayList()

    val reqAllMaterialClassRoom : AllCourseMaterialRequest = AllCourseMaterialRequest()

    val listClassRoomProgress : ArrayList<ClassRoomProgressModel> = ArrayList()
    val reqAllClassRoomProgress  : AllClassRoomProgressRequest = AllClassRoomProgressRequest()

    lateinit var adapterImageDetailCourse : AdapterImageDetailCourse

    val reqAllCourseDetails = AllCourseDetailRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_classroom)
        initWidget()
    }

    fun initWidget(){

        context = this@MaterialClassRoomActivity

        classRoomModel = intent.getSerializableExtra("data") as ClassRoomModel

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        reqAllCourseDetails.Limit = limit_load
        reqAllCourseDetails.Offset = 0
        reqAllCourseDetails.CourseId = classRoomModel.Course.Id

        title_course_material.text = classRoomModel.Course.CourseName

        back_imageview.setOnClickListener {
            finish()
        }

        setAdapterMaterial()
        setMaterialListAndProgress()
        setImageCourse()
    }

    fun getClassRoomProgress(){
        presenter.getAllClassRoomProgress(reqAllClassRoomProgress)
    }

    fun getAllClassRoomMaterials(){
        presenter.getAllCourseMaterial(reqAllMaterialClassRoom)
    }

    fun setAdapterMaterial(){
        adapterMaterialClassRoom = AdapterMaterialClassRoom(context,listMaterialClassRoom){courseMaterialModel, i ->
            val intent = Intent(context,MaterialDetailClassRoomActivity::class.java)
            intent.putExtra("pos",i)
            intent.putExtra("classroom",classRoomModel)
            startActivityForResult(intent,101)
        }
        material_classroom_recycleview.adapter = adapterMaterialClassRoom
        material_classroom_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }

    fun setMaterialListAndProgress(){

        listMaterialClassRoom.clear()

        reqAllMaterialClassRoom.Limit = limit_load
        reqAllMaterialClassRoom.Offset = 0
        reqAllMaterialClassRoom.CourseId = classRoomModel.Course.Id

        reqAllClassRoomProgress.Limit = limit_load
        reqAllClassRoomProgress.Offset = 0
        reqAllClassRoomProgress.ClassroomId = classRoomModel.Id

        loadmore_textview.setOnClickListener {
            reqAllMaterialClassRoom.Offset += limit_load
            reqAllClassRoomProgress.Offset += limit_load
            presenter.getAllCourseMaterial(reqAllMaterialClassRoom)
        }

        material_classroom_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllMaterialClassRoom.Offset += limit_load
                reqAllClassRoomProgress.Offset += limit_load
                presenter.getAllCourseMaterial(reqAllMaterialClassRoom)
            }
        })

        loadmore_textview.visibility = View.GONE
        getAllClassRoomMaterials()
    }

    fun setImageCourse(){
        adapterImageDetailCourse = AdapterImageDetailCourse(context,classRoomModel.Course.CourseDetails)
        image_course_recycleview.adapter = adapterImageDetailCourse
        image_course_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(image_course_recycleview)

        setDetailsContentBaseOnSelectedImage()

        classRoomModel.Course.CourseDetails.clear()
        presenter.getAllCourseDetails(reqAllCourseDetails)
    }

    fun setDetailsContentBaseOnSelectedImage(){
        if (classRoomModel.Course.CourseDetails.isNotEmpty()){
            image_course_recycleview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    setTextCourse((recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
                }
            })
            setTextCourse(0)
        }
    }
    fun setTextCourse(pos : Int){
        overview_textview.text = classRoomModel.Course.CourseDetails.get(pos).OverviewText
        description_textview.text = classRoomModel.Course.CourseDetails.get(pos).DescriptionText
    }

    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listMaterialClassRoom.isEmpty() || forceShow) View.VISIBLE else View.GONE
        loadmore_textview.visibility = if (listMaterialClassRoom.isEmpty() || forceShow) View.GONE else View.VISIBLE
        material_classroom_recycleview.visibility = if (listMaterialClassRoom.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }

    fun validateMaterialProgress(){
        for (i in listClassRoomProgress){
            for (o in listMaterialClassRoom){
                if (o.Id == i.CourseMaterialId){
                    o.IsCompleted = true
                }
            }
        }
        adapterMaterialClassRoom.notifyDataSetChanged()
    }


    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        checkNoResultFound(true)
    }

    override fun onGetAllCourseMaterial(s: AllCourseMaterialResponse) {
        listMaterialClassRoom.addAll(s.Data.CourseMaterialList)
        adapterMaterialClassRoom.notifyDataSetChanged()
        checkNoResultFound(false)
        loadmore_textview.text = if (s.Data.CourseMaterialList.isEmpty()) "" else loadmore_textview.text
        getClassRoomProgress()
    }

    override fun onGetAllClassRoomProgress(s: AllClassRoomProgressResponse) {
        listClassRoomProgress.addAll(s.Data.ClassRoomProgressList)
        validateMaterialProgress()
    }

    override fun onGetAllCourseDetails(s: AllCourseDetailResponse) {
        classRoomModel.Course.CourseDetails.addAll(s.Data.CourseDetailList)
        adapterImageDetailCourse.notifyDataSetChanged()
        setDetailsContentBaseOnSelectedImage()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK){
            setMaterialListAndProgress()
        }
    }
}
