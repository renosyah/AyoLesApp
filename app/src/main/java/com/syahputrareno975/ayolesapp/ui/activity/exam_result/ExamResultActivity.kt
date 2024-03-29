package com.syahputrareno975.ayolesapp.ui.activity.exam_result

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.ClassRoomExamResultModel
import com.syahputrareno975.ayolesapp.model.classRoomQualification.ClassRoomQualificationModel.Companion.STATUS_NOT_PASS_EXAM
import com.syahputrareno975.ayolesapp.model.classRoomQualification.ClassRoomQualificationModel.Companion.STATUS_PASS_EXAM
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.activity.certificate_view.CertificateActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterExamResult
import kotlinx.android.synthetic.main.activity_exam_result.*
import javax.inject.Inject
import kotlin.math.round


class ExamResultActivity : AppCompatActivity(), ExamResultActivityContract.View {

    @Inject
    lateinit var presenter: ExamResultActivityContract.Presenter
    companion object {
        val limit_load = 5
    }

    lateinit var context: Context
    lateinit var classRoomModel : ClassRoomModel

    lateinit var adapterExamResult : AdapterExamResult
    val listExamResult : ArrayList<ClassRoomExamResultModel> = ArrayList()

    val reqAllExamResult : AllClassRoomExamResultRequest = AllClassRoomExamResultRequest()

    var exam_status = STATUS_NOT_PASS_EXAM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_result)
        initWidget()
    }

    fun initWidget(){
        this.context = this@ExamResultActivity

        classRoomModel = intent.getSerializableExtra("data") as ClassRoomModel

        reqAllExamResult.Offset = 0
        reqAllExamResult.Limit = limit_load
        reqAllExamResult.LimitAnswer = 4
        reqAllExamResult.ClassRoomId = classRoomModel.Id

        title_exam_result_textview.text = "${getString(R.string.exam_result_title)} ${classRoomModel.Course.CourseName}"

        back_imageview.setOnClickListener {
            finish()
        }

        not_found.visibility = View.GONE

        request_certificate_button.setOnClickListener {
            // request score
            // and cert
            if (exam_status == STATUS_NOT_PASS_EXAM){
                Toast.makeText(context,getString(R.string.not_qualified),Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            presenter.getOneClassRoomCertificate(OneClassRoomCertificateRequest(classRoomModel.Id),false)
        }
        linear_layout_request_cert.visibility = View.GONE
        request_certificate_button.visibility = View.GONE

        exam_result_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllExamResult.Offset += limit_load
                presenter.getAllClassRoomExamResult(reqAllExamResult,false)
            }
        })

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        getAllExamResult()
    }

    fun getAllExamResult(){
        adapterExamResult = AdapterExamResult(context,listExamResult)
        exam_result_recycleview.adapter = adapterExamResult
        exam_result_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        presenter.getAllClassRoomExamResult(reqAllExamResult,true)
        presenter.getOneClassRoomQualification(OneClassRoomQualificationRequest(classRoomModel.Id),false)
    }

    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listExamResult.isEmpty() || forceShow) View.VISIBLE else View.GONE
        exam_result_recycleview.visibility = if (listExamResult.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }

    override fun showProgressOnGetAllClassRoomExamResult(show: Boolean) {
        loading_data_exam_result.visibility = if (show) View.VISIBLE else View.GONE
        exam_result_recycleview.visibility = if (show) View.GONE else View.VISIBLE
        request_certificate_button.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnGetAllClassRoomExamResult(error: String) {
        loading_data_exam_result.visibility = View.GONE
        request_certificate_button.visibility = View.GONE
        checkNoResultFound(true)
    }

    override fun showProgressOnGetOneClassRoomCertificate(show: Boolean) {

    }

    override fun showErrorOnGetOneClassRoomCertificate(error: String) {

    }

    override fun showProgressOnGetOneClassRoomQualification(show: Boolean) {

    }

    override fun showErrorOnGetOneClassRoomQualification(error: String) {

    }

    override fun onGetAllClassRoomExamResult(s: AllClassRoomExamResultResponse) {
        listExamResult.addAll(s.Data.classRoomExamResultModelList)
        adapterExamResult.notifyDataSetChanged()
        checkNoResultFound(false)
    }

    override fun onGetOneClassRoomCertificate(s: OneClassRoomCertificateResponse) {
        val intent = Intent(context,CertificateActivity::class.java)
        intent.putExtra("data",classRoomModel)
        startActivity(intent)
    }

    override fun onGetOneClassRoomQualification(s: OneClassRoomQualificationResponse) {
        val total_exam = s.Data.ClassRoomQualificationDetail.CourseQualification.CourseExamTotal
        val total_score = s.Data.ClassRoomQualificationDetail.TotalScore

        textview_total_exam.text = "${total_exam}"
        textview_total_correct_answered_exam.text = "${total_score}"
        textview_exam_score.text = "${ round((total_score.toFloat() / total_exam.toFloat()) * 100.0)}"

        exam_status = s.Data.ClassRoomQualificationDetail.Status
        linear_layout_request_cert.visibility = View.VISIBLE

        when {
            s.Data.ClassRoomQualificationDetail.Status == STATUS_PASS_EXAM -> {
                textview_exam_score.setTextColor(Color.GREEN)
            }
            s.Data.ClassRoomQualificationDetail.Status == STATUS_NOT_PASS_EXAM ->
                textview_exam_score.setTextColor(Color.RED)
            else ->
                Toast.makeText(context,getString(R.string.please_finish_exam),Toast.LENGTH_LONG).show()
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
