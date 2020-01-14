package com.syahputrareno975.ayolesapp.ui.activity.exam_result

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse

class ExamResultActivityContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetAllClassRoomExamResult(s : AllClassRoomExamResultResponse)
        fun showProgressOnGetAllClassRoomExamResult(show: Boolean)
        fun showErrorOnGetAllClassRoomExamResult(error: String)

        fun onGetOneClassRoomCertificate(s : OneClassRoomCertificateResponse)
        fun showProgressOnGetOneClassRoomCertificate(show: Boolean)
        fun showErrorOnGetOneClassRoomCertificate(error: String)

        fun onGetOneClassRoomQualification(s : OneClassRoomQualificationResponse)
        fun showProgressOnGetOneClassRoomQualification(show: Boolean)
        fun showErrorOnGetOneClassRoomQualification(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClassRoomExamResult(r : AllClassRoomExamResultRequest,enableLoading:Boolean)
        fun getOneClassRoomCertificate(r : OneClassRoomCertificateRequest,enableLoading:Boolean)
        fun getOneClassRoomQualification(r : OneClassRoomQualificationRequest,enableLoading:Boolean)
    }
}