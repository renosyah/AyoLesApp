package com.syahputrareno975.ayolesapp.ui.activity.material_classroom

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse

class MaterialClassRoomActivityContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetAllClassRoomProgress(s : AllClassRoomProgressResponse)
        fun showProgressOnGetAllClassRoomProgress(show: Boolean)
        fun showErrorOnGetAllClassRoomProgress(error: String)

        fun onGetAllCourseMaterial(s : AllCourseMaterialResponse)
        fun showProgressOnGetAllCourseMaterial(show: Boolean)
        fun showErrorOnGetAllCourseMaterial(error: String)

        fun onGetAllCourseDetails(s : AllCourseDetailResponse)
        fun showProgressOnGetAllCourseDetails(show: Boolean)
        fun showErrorOnGetAllCourseDetails(error: String)

        fun onGetOneClassRoomQualification(s : OneClassRoomQualificationResponse)
        fun showProgressOnGetOneClassRoomQualification(show: Boolean)
        fun showErrorOnGetOneClassRoomQualification(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClassRoomProgress(r : AllClassRoomProgressRequest,enableLoading :Boolean)
        fun getAllCourseMaterial(r : AllCourseMaterialRequest,enableLoading :Boolean)
        fun getAllCourseDetails(r : AllCourseDetailRequest,enableLoading :Boolean)
        fun getOneClassRoomQualification(r : OneClassRoomQualificationRequest,enableLoading :Boolean)
    }
}