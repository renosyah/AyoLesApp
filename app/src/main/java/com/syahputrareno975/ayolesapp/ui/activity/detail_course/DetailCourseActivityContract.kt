package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomResponse

class DetailCourseActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onAddClassRoom(s : AddClassRoomResponse)
        fun onGetOneClassRoomById(s : OneClassByIdRoomResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun addClassRoom(r : AddClassRoomRequest)
        fun getOneClassRoomById(r : OneClassByIdRoomRequest)
    }
}