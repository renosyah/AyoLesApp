package com.syahputrareno975.ayolesapp.ui.activity.material_detail

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailRequest
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailResponse

class MaterialDetailClassRoomActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllCourseMaterialDetail(s : AllCourseMaterialDetailResponse)
        fun onGetAllCourseMaterial(s : AllCourseMaterialResponse)
        fun onAddCourseMaterialProgress(s : AddClassRoomProgressResponse,navCode: Int)
        fun onGetOneClassRoomCertificate(r : OneClassRoomCertificateResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun addCourseMaterialProgress(r : AddClassRoomProgressRequest,navCode: Int)
        fun getAllCourseMaterial(r : AllCourseMaterialRequest)
        fun getAllCourseMaterialDetail(r : AllCourseMaterialDetailRequest)
        fun getOneClassRoomCertificate(r : OneClassRoomCertificateRequest)
    }
}