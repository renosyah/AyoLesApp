package com.syahputrareno975.ayolesapp.ui.activity.register

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.student.RegisterRequest
import com.syahputrareno975.ayolesapp.model.student.RegisterResponse

class RegisterActivityContract {
    interface View : BaseContract.View {

        // add more for request
        fun onRegister(s : RegisterResponse)
        fun showProgressOnRegister(show: Boolean)
        fun showErrorOnRegister(error: String)

    }

    interface Presenter : BaseContract.Presenter<View> {

        // add for request
        fun register(r : RegisterRequest,enableLoading : Boolean)
    }
}