package com.syahputrareno975.ayolesapp.ui.activity.certificate_view

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse

class CertificateActivityContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetOneClassRoomCertificate(s : OneClassRoomCertificateResponse)
        fun showProgressOnGetOneClassRoomCertificate(show: Boolean)
        fun showErrorOnGetOneClassRoomCertificate(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getOneClassRoomCertificate(r : OneClassRoomCertificateRequest,enableLoading:Boolean)
    }
}