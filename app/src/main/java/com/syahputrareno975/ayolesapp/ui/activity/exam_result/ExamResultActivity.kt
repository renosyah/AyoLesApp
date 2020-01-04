package com.syahputrareno975.ayolesapp.ui.activity.exam_result

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
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
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterExamResult
import kotlinx.android.synthetic.main.activity_exam_result.*
import javax.inject.Inject


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

        title_exam_result_textview.text = "${classRoomModel.Course.CourseName}'s Exam Result"

        request_certificate_button.setOnClickListener {
            // request score
            // and cert
            presenter.getOneClassRoomCertificate(OneClassRoomCertificateRequest(classRoomModel.Id))
        }

        exam_result_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllExamResult.Offset += limit_load
                presenter.getAllClassRoomExamResult(reqAllExamResult)
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

        presenter.getAllClassRoomExamResult(reqAllExamResult)
    }

    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listExamResult.isEmpty() || forceShow) View.VISIBLE else View.GONE
        exam_result_recycleview.visibility = if (listExamResult.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        checkNoResultFound(true)
    }

    override fun onGetAllClassRoomExamResult(s: AllClassRoomExamResultResponse) {
        listExamResult.addAll(s.Data.classRoomExamResultModelList)
        adapterExamResult.notifyDataSetChanged()
        checkNoResultFound(false)
    }

    override fun onGetOneClassRoomCertificate(s: OneClassRoomCertificateResponse) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${RetrofitService.baseURL}cert/${s.Data.ClassRoomCertificateDetail.HashId}"))
        startActivity(browserIntent)
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
