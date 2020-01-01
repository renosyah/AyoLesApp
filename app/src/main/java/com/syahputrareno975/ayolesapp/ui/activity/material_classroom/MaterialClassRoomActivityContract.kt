package com.syahputrareno975.ayolesapp.ui.activity.material_classroom

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse

class MaterialClassRoomActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllClassRoomProgress(s : AllClassRoomProgressResponse)
        fun onGetAllCourseMaterial(s : AllCourseMaterialResponse)
        fun onGetAllCourseDetails(s : AllCourseDetailResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClassRoomProgress(r : AllClassRoomProgressRequest)
        fun getAllCourseMaterial(r : AllCourseMaterialRequest)
        fun getAllCourseDetails(r : AllCourseDetailRequest)
    }
}