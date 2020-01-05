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

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllClassRoomExamResult(s : AllClassRoomExamResultResponse)
        fun onGetOneClassRoomCertificate(s : OneClassRoomCertificateResponse)
        fun onGetOneClassRoomQualification(s : OneClassRoomQualificationResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClassRoomExamResult(r : AllClassRoomExamResultRequest)
        fun getOneClassRoomCertificate(r : OneClassRoomCertificateRequest)
        fun getOneClassRoomQualification(r : OneClassRoomQualificationRequest)
    }
}