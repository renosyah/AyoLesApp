package com.syahputrareno975.ayolesapp.ui.activity.update_profile

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse
import com.syahputrareno975.ayolesapp.model.student.UpdateRequest
import com.syahputrareno975.ayolesapp.model.student.UpdateResponse

class UpdateProfileActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetOneStudent(s : OneStudentResponse)
        fun onOneStudentUpdated(s : UpdateResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getOneStudent(r : OneStudentRequest)
        fun updateOneStudent(r : UpdateRequest)
    }
}