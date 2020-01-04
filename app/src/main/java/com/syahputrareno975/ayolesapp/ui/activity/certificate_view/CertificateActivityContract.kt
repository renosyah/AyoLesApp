package com.syahputrareno975.ayolesapp.ui.activity.certificate_view

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse

class CertificateActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetOneClassRoomCertificate(s : OneClassRoomCertificateResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getOneClassRoomCertificate(r : OneClassRoomCertificateRequest)
    }
}