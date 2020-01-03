package com.syahputrareno975.ayolesapp.model.classRoomCertificate

import com.syahputrareno975.ayolesapp.model.BaseModel

class OneClassRoomCertificateRequest : BaseModel {
    var ClassRoomID = ""

    constructor(ClassRoomID: String) {
        this.ClassRoomID = ClassRoomID
    }

    fun toSchema() : String {
        return "query {\n" +
                "classroom_certificate_detail(\n" +
                "classroom_id: \"${ClassRoomID}\"\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "hash_id\n" +
                " }\n" +
                "} "
    }

}