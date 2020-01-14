package com.syahputrareno975.ayolesapp.ui.activity.material_detail

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailRequest
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailResponse

class MaterialDetailClassRoomActivityContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetAllCourseMaterialDetail(s : AllCourseMaterialDetailResponse)
        fun showProgressOnGetAllCourseMaterialDetail(show: Boolean)
        fun showErrorOnGetAllCourseMaterialDetail(error: String)

        fun onGetAllCourseMaterial(s : AllCourseMaterialResponse)
        fun showProgressOnGetAllCourseMaterial(show: Boolean)
        fun showErrorOnGetAllCourseMaterial(error: String)

        fun onAddCourseMaterialProgress(s : AddClassRoomProgressResponse,navCode: Int)
        fun showProgressOnAddCourseMaterialProgress(show: Boolean)
        fun showErrorOnAddCourseMaterialProgress(error: String)

        fun onGetOneClassRoomQualification(s : OneClassRoomQualificationResponse)
        fun showProgressOnGetOneClassRoomQualification(show: Boolean)
        fun showErrorOnGetOneClassRoomQualification(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun addCourseMaterialProgress(r : AddClassRoomProgressRequest,navCode: Int,enableLoading : Boolean)
        fun getAllCourseMaterial(r : AllCourseMaterialRequest,enableLoading : Boolean)
        fun getAllCourseMaterialDetail(r : AllCourseMaterialDetailRequest,enableLoading : Boolean)
        fun getOneClassRoomQualification(r : OneClassRoomQualificationRequest,enableLoading : Boolean)
    }
}