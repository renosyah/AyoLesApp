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

        // add more for request
        fun onGetAllExam(s : AllCourseExamResponse)
        fun showProgressOnGetAllExam(show: Boolean)
        fun showErrorOnGetAllExam(error: String)

        fun onAddClassRoomExamProgress(s : AddClassRoomExamProgressResponse,posExampSubmited : Int)
        fun showProgressOnAddClassRoomExamProgress(show: Boolean)
        fun showErrorOnAddClassRoomExamProgress(error: String)

        //fun onDeleteClassRoomExamProgress(s : DeleteClassRoomExamProgressResponse)

        fun onGetAllClassRoomExamProgress(s : AllClassRoomExamProgressResponse)
        fun showProgressOnGetAllClassRoomExamProgress(show: Boolean)
        fun showErrorOnGetAllClassRoomExamProgress(error: String)

        fun onAddClassRoomCertificate(r : AddClassRoomCertificateResponse)
        fun showProgressOnAddClassRoomCertificate(show: Boolean)
        fun showErrorOnAddClassRoomCertificate(error: String)

    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllExam(r : AllCourseExamRequest,enableLoading :Boolean)
        fun addClassRoomExamProgress(r : AddClassRoomExamProgressRequest,posExampSubmited : Int,enableLoading :Boolean)
        //fun deleteClassRoomExamProgress(r : DeleteClassRoomExamProgressRequest)
        fun getAllClassRoomExamProgress(r : AllClassRoomExamProgressRequest,enableLoading :Boolean)
        fun addClassRoomCertificate(r : AddClassRoomCertificateRequest,enableLoading :Boolean)
    }
}