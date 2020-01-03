package com.syahputrareno975.ayolesapp.model.classRoomCertificate

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllClassRoomCertificateResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassRoomCertificateList = ClassRoomCertificateList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomCertificateList : BaseModel {
        @SerializedName("classroom_certificate_list")
        var ClassRoomCertificateList : ArrayList<ClassRoomCertificateModel> = ArrayList<ClassRoomCertificateModel>()
    }
}