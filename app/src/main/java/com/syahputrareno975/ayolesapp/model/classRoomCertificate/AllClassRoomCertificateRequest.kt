package com.syahputrareno975.ayolesapp.model.classRoomCertificate

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllClassRoomCertificateRequest : BaseModel {
    var StudentId = ""
    var OrderBy = "classroom_certificate.create_at"
    var OrderDir = "asc"
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(StudentId: String, OrderBy: String, OrderDir: String, Offset: Int, Limit: Int) {
        this.StudentId = StudentId
        this.OrderBy = OrderBy
        this.OrderDir = OrderDir
        this.Offset = Offset
        this.Limit = Limit
    }

    fun toSchema() : String {
        return "query {\n" +
                "classroom_certificate_list(\n" +
                "student_id:\"${StudentId}\",\n" +
                "order_by:\"${OrderBy}\",\n" +
                "order_dir:\"${OrderDir}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit}\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "hash_id\n" +
                " }\n" +
                "}"
    }
}