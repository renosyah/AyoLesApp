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

        // add more for request
        fun onGetAllCourseDetails(r : AllCourseDetailResponse)
        fun showProgressOnGetAllCourseDetails(show: Boolean)
        fun showErrorOnGetAllCourseDetails(error: String)

        fun onAddClassRoom(s : AddClassRoomResponse)
        fun showProgressOnAddClassRoom(show: Boolean)
        fun showErrorOnAddClassRoom(error: String)

        fun onGetOneClassRoomById(s : OneClassByIdRoomResponse)
        fun showProgressOnGetOneClassRoomById(show: Boolean)
        fun showErrorOnGetOneClassRoomById(error: String)

        fun onGetOneCourseQualification(s : OneCourseQualificationResponse)
        fun showProgressOnGetOneCourseQualification(show: Boolean)
        fun showErrorOnGetOneCourseQualification(error: String)

    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllCourseDetails(r : AllCourseDetailRequest,enableLoading :Boolean)
        fun addClassRoom(r : AddClassRoomRequest,enableLoading :Boolean)
        fun getOneClassRoomById(r : OneClassByIdRoomRequest,enableLoading :Boolean)
        fun getOneCourseQualification(r : OneCourseQualificationRequest,enableLoading :Boolean)
    }
}