package com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse

class FragmentProfileContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllClass(s : AllClassRoomResponse)
        fun onGetOneStudent(s : OneStudentResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClass(r : AllClassRoomRequest)
        fun getOneStudent(r : OneStudentRequest)
    }
}