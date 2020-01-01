package com.syahputrareno975.ayolesapp.ui.activity.register

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.student.RegisterRequest
import com.syahputrareno975.ayolesapp.model.student.RegisterResponse

class RegisterActivityContract {
    interface View : BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)

        fun showError(error: String)

        // add more for request
        fun onRegister(s : RegisterResponse)

    }

    interface Presenter : BaseContract.Presenter<View> {

        // add for request
        fun register(r : RegisterRequest)
    }
}