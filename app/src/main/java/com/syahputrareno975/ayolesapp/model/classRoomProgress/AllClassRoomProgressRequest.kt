package com.syahputrareno975.ayolesapp.model.classRoomProgress

import com.syahputrareno975.ayolesapp.model.BaseModel

class AllClassRoomProgressRequest : BaseModel {
    var ClassroomId : String = ""
    var Offset = 0
    var Limit = 10

    constructor()

    constructor(ClassroomId: String, Offset: Int, Limit: Int) {
        this.ClassroomId = ClassroomId
        this.Offset = Offset
        this.Limit = Limit
    }

    fun toSchema() : String {
        return "query {\n" +
                "classroom_progress_list(\n" +
                "classroom_id:\"${ClassroomId}\",\n" +
                "offset:${Offset},\n" +
                "limit:${Limit}\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "course_material_id\n" +
                " }\n" +
                "}"
    }
}