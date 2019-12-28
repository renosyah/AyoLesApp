package com.syahputrareno975.ayolesapp.ui.fragment.fragment_class

import com.syahputrareno975.ayolesapp.base.BaseContract

class FragmentClassContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request

    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
    }
}