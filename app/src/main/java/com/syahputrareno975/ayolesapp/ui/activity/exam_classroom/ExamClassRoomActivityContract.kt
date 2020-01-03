package com.syahputrareno975.ayolesapp.ui.activity.exam_classroom

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.*
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamRequest
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamResponse

class ExamClassRoomActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllExam(s : AllCourseExamResponse)
        fun onAddClassRoomExamProgress(s : AddClassRoomExamProgressResponse,posExampSubmited : Int)
        //fun onDeleteClassRoomExamProgress(s : DeleteClassRoomExamProgressResponse)
        fun onGetAllClassRoomExamProgress(s : AllClassRoomExamProgressResponse)
        fun onAddClassRoomCertificate(r : AddClassRoomCertificateResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllExam(r : AllCourseExamRequest)
        fun addClassRoomExamProgress(r : AddClassRoomExamProgressRequest,posExampSubmited : Int)
        //fun deleteClassRoomExamProgress(r : DeleteClassRoomExamProgressRequest)
        fun getAllClassRoomExamProgress(r : AllClassRoomExamProgressRequest)
        fun addClassRoomCertificate(r : AddClassRoomCertificateRequest)
    }
}