package com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AllClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AllClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse

class FragmentProfileContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetAllClass(s : AllClassRoomResponse)
        fun showProgressOnGetAllClass(show: Boolean)
        fun showErrorOnGetAllClass(error: String)

        fun onGetOneStudent(s : OneStudentResponse)
        fun showProgressOnGetOneStudent(show: Boolean)
        fun showErrorOnGetOneStudent(error: String)

        fun onGetAllClassRoomCertificate(s : AllClassRoomCertificateResponse)
        fun showProgressOnGetAllClassRoomCertificate(show: Boolean)
        fun showErrorOnGetAllClassRoomCertificate(error: String)

    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClass(r : AllClassRoomRequest,enableLoading : Boolean)
        fun getOneStudent(r : OneStudentRequest,enableLoading : Boolean)
        fun getAllClassRoomCertificate(r : AllClassRoomCertificateRequest,enableLoading : Boolean)
    }
}