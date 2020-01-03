package com.syahputrareno975.ayolesapp.model.classRoomCertificate

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel

class AddClassRoomCertificateResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassRoomCertificateRegister = ClassRoomCertificateRegister()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomCertificateRegister : BaseModel {
        @SerializedName("classroom_certificate_register")
        var ClassRoomCertificateRegister : ClassRoomCertificateModel = ClassRoomCertificateModel()
    }
}