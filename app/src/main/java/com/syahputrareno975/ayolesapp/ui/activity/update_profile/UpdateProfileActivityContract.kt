package com.syahputrareno975.ayolesapp.ui.activity.update_profile

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse
import com.syahputrareno975.ayolesapp.model.student.UpdateRequest
import com.syahputrareno975.ayolesapp.model.student.UpdateResponse

class UpdateProfileActivityContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetOneStudent(s : OneStudentResponse)
        fun showProgressOnGetOneStudent(show: Boolean)
        fun showErrorOnGetOneStudent(error: String)

        fun onOneStudentUpdated(s : UpdateResponse)
        fun showProgressOnOneStudentUpdated(show: Boolean)
        fun showErrorOnOneStudentUpdated(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getOneStudent(r : OneStudentRequest,enableLoading : Boolean)
        fun updateOneStudent(r : UpdateRequest,enableLoading : Boolean)
    }
}