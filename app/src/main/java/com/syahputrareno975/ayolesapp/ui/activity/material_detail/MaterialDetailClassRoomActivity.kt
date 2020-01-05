package com.syahputrareno975.ayolesapp.ui.activity.material_detail

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.ClassRoomQualificationModel.Companion.STATUS_NO_PROGRESS
import com.syahputrareno975.ayolesapp.model.classRoomQualification.ClassRoomQualificationModel.Companion.STATUS_PASS_EXAM
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.CourseMaterialModel
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailRequest
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailResponse
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.CourseMaterialDetailModel
import com.syahputrareno975.ayolesapp.ui.activity.exam_classroom.ExamClassRoomActivity
import com.syahputrareno975.ayolesapp.ui.activity.exam_result.ExamResultActivity
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterMaterialDetail
import com.syahputrareno975.ayolesapp.util.EmptyUUID
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.activity_material_detail_class_room.*
import kotlinx.android.synthetic.main.activity_material_detail_class_room.not_found
import kotlinx.android.synthetic.main.activity_search_course.*
import javax.inject.Inject

class MaterialDetailClassRoomActivity : AppCompatActivity(),MaterialDetailClassRoomActivityContract.View {


    @Inject
    lateinit var presenter: MaterialDetailClassRoomActivityContract.Presenter

    companion object {
        val limit_load = 10
        val limit_load_material = 1
        val CODE_EXAM = 300
        val CODE_NEXT = 200
        val CODE_PREVIOUS = 100
    }

    lateinit var context: Context
    lateinit var courseMaterial: CourseMaterialModel
    val reqAllMaterial: AllCourseMaterialRequest = AllCourseMaterialRequest()

    lateinit var classRoomModel: ClassRoomModel

    lateinit var adapterMaterialDetail: AdapterMaterialDetail
    val listMaterialDetail : ArrayList<CourseMaterialDetailModel> = ArrayList()

    val reqAllMaterialDetail : AllCourseMaterialDetailRequest = AllCourseMaterialDetailRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_detail_class_room)
        initWidget()
    }

    fun initWidget(){

        this.context = this@MaterialDetailClassRoomActivity

        classRoomModel = intent.getSerializableExtra("classroom") as ClassRoomModel
        reqAllMaterial.Offset = intent.getIntExtra("pos",0)
        reqAllMaterial.Limit = 2
        reqAllMaterial.CourseId = classRoomModel.Course.Id

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        back_imageview.setOnClickListener {
            onBackPressed()
        }

        exam_button.setOnClickListener {
            presenter.addCourseMaterialProgress(AddClassRoomProgressRequest(classRoomModel.Id,courseMaterial.Id), CODE_EXAM)
        }

        presenter.getAllCourseMaterial(reqAllMaterial)
    }

    fun setLayout(){

        listMaterialDetail.clear()

        title_textview.text = courseMaterial.Title
        layout_progress.visibility = View.GONE

        reqAllMaterialDetail.Offset = 0
        reqAllMaterialDetail.CourseMaterialId = courseMaterial.Id
        reqAllMaterialDetail.Limit = limit_load

        material_detail_nextedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllMaterialDetail.Offset += limit_load
                presenter.getAllCourseMaterialDetail(reqAllMaterialDetail)
            }
        })

        previous_button.visibility = if (reqAllMaterial.Offset - 1 < 0) View.INVISIBLE else View.VISIBLE
        previous_button.setOnClickListener {
            // save progress
            presenter.addCourseMaterialProgress(AddClassRoomProgressRequest(classRoomModel.Id,courseMaterial.Id), CODE_PREVIOUS)
        }

        next_button.setOnClickListener {
            // save progress
            presenter.addCourseMaterialProgress(AddClassRoomProgressRequest(classRoomModel.Id,courseMaterial.Id), CODE_NEXT)
        }

        getAllMaterialDetail()
    }


    fun getAllMaterialDetail(){
        adapterMaterialDetail = AdapterMaterialDetail(context,listMaterialDetail){courseMaterialDetailModel, i ->

        }
        material_detail_recycleview.adapter = adapterMaterialDetail
        material_detail_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        presenter.getAllCourseMaterialDetail(reqAllMaterialDetail)
    }

    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listMaterialDetail.isEmpty() || forceShow) View.VISIBLE else View.GONE
        material_detail_recycleview.visibility = if (listMaterialDetail.isEmpty() || forceShow) View.GONE else View.VISIBLE
        layout_progress.visibility = if (listMaterialDetail.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }

    override fun showProgress(show: Boolean) {
        not_found.visibility = View.GONE
    }

    override fun showError(error: String) {
        checkNoResultFound(true)
    }

    override fun onGetAllCourseMaterial(s: AllCourseMaterialResponse) {
        // if data size got 1 instead 2
        // next button will be hide
        next_button.visibility = if (s.Data.CourseMaterialList.size <= 1) View.GONE else View.VISIBLE

        // and exam button
        // will show
        exam_button.visibility = if (s.Data.CourseMaterialList.size <= 1) View.VISIBLE else View.GONE
        if (s.Data.CourseMaterialList.size <= 1){
            presenter.getOneClassRoomQualification(OneClassRoomQualificationRequest(classRoomModel.Id))
        }

        // by using loop then break
        // to get only data from first index
        for (i in s.Data.CourseMaterialList){
            courseMaterial = i
            break
        }
        setLayout()
    }

    override fun onGetAllCourseMaterialDetail(s: AllCourseMaterialDetailResponse) {
        listMaterialDetail.addAll(s.Data.CourseMaterialDetailList)
        adapterMaterialDetail.notifyDataSetChanged()
        checkNoResultFound(false)
    }

    override fun onAddCourseMaterialProgress(s: AddClassRoomProgressResponse,navCode: Int) {
        // save success
        // load next or previous material
        when (navCode){
            CODE_NEXT -> {
                reqAllMaterial.Offset += limit_load_material
                presenter.getAllCourseMaterial(reqAllMaterial)
            }
            CODE_PREVIOUS -> {
                reqAllMaterial.Offset -= limit_load_material
                presenter.getAllCourseMaterial(reqAllMaterial)
            }
            CODE_EXAM -> {

                val intent = Intent(context, ExamClassRoomActivity::class.java)
                intent.putExtra("data",classRoomModel)
                startActivity(intent)
                finish()

            }
        }
    }
    override fun onGetOneClassRoomQualification(s: OneClassRoomQualificationResponse) {
        exam_button.text = (if (s.Data.ClassRoomQualificationDetail.Status == STATUS_NO_PROGRESS) "Start Exam" else "Exam Result")
        if (s.Data.ClassRoomQualificationDetail.Status != STATUS_NO_PROGRESS) {
            exam_button.setOnClickListener {

                // go to exam result
                // activity
                val intent = Intent(context, ExamResultActivity::class.java)
                intent.putExtra("data", classRoomModel)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun injectDependency(){
        val listcomponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        listcomponent.inject(this)
    }
}
