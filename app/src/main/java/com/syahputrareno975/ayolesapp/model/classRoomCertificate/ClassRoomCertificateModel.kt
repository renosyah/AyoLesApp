package com.syahputrareno975.ayolesapp.model.classRoomCertificate

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class ClassRoomCertificateModel: BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("classroom_id")
    var ClassroomId : String = ""

    @SerializedName("hash_id")
    var HashId : String = ""

    constructor()

    constructor(Id: String, ClassroomId: String, HashId: String) {
        this.Id = Id
        this.ClassroomId = ClassroomId
        this.HashId = HashId
    }


}