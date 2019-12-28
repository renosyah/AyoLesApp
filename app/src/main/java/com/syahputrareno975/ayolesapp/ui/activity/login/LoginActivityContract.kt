package com.syahputrareno975.ayolesapp.ui.activity.login

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.login.LoginRequest
import com.syahputrareno975.ayolesapp.model.login.LoginResponse

class LoginActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onLogin(result: LoginResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun login(login : LoginRequest)
    }
}