package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseQualification.OneCourseQualificationRequest
import com.syahputrareno975.ayolesapp.model.courseQualification.OneCourseQualificationResponse

class DetailCourseActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllCourseDetails(r : AllCourseDetailResponse)
        fun onAddClassRoom(s : AddClassRoomResponse)
        fun onGetOneClassRoomById(s : OneClassByIdRoomResponse)
        fun onGetOneCourseQualification(s : OneCourseQualificationResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllCourseDetails(r : AllCourseDetailRequest)
        fun addClassRoom(r : AddClassRoomRequest)
        fun getOneClassRoomById(r : OneClassByIdRoomRequest)
        fun getOneCourseQualification(r : OneCourseQualificationRequest)
    }
}