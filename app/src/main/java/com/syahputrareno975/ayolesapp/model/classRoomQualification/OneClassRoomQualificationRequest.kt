package com.syahputrareno975.ayolesapp.model.classRoomQualification

import com.syahputrareno975.ayolesapp.model.BaseModel

class OneClassRoomQualificationRequest:BaseModel {
    var ClassRoomId : String = ""

    constructor(ClassRoomId: String) {
        this.ClassRoomId = ClassRoomId
    }

    fun toSchema() : String {
        return "query {\n" +
                "class_qualification_detail(\n" +
                "classroom_id: \"${ClassRoomId}\"\n" +
                ")\n" +
                "{\n" +
                "classroom_id,\n" +
                "total_score,\n" +
                "status,\n" +
                "course_qualification {\n" +
                "id,\n" +
                "course_id,\n" +
                "course_level,\n" +
                "min_score,\n" +
                "course_material_total,\n" +
                "course_exam_total\n" +
                "}\n" +
                "}\n" +
                "}"
    }
}