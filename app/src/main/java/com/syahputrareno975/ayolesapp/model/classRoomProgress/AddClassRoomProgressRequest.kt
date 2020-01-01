package com.syahputrareno975.ayolesapp.model.classRoomProgress

import com.syahputrareno975.ayolesapp.model.BaseModel

class AddClassRoomProgressRequest : BaseModel {
    var  ClassroomId  = ""
    var  CourseMaterialId = ""

    constructor(ClassroomId: String, CourseMaterialId: String) {
        this.ClassroomId = ClassroomId
        this.CourseMaterialId = CourseMaterialId
    }
    
    fun toSchema() : String {
        return "mutation {\n" +
                "classroom_progress_register(\n" +
                "classroom_id :\"${ClassroomId}\",\n" +
                "course_material_id :\"${CourseMaterialId}\"\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "course_material_id\n" +
                " }\n" +
                "}"
    }
}