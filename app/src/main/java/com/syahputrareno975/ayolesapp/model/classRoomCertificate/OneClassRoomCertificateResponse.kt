package com.syahputrareno975.ayolesapp.model.classRoomCertificate

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class OneClassRoomCertificateResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassRoomCertificateDetail = ClassRoomCertificateDetail()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomCertificateDetail : BaseModel {
        @SerializedName("classroom_certificate_detail")
        var ClassRoomCertificateDetail : ClassRoomCertificateModel = ClassRoomCertificateModel()
    }
}