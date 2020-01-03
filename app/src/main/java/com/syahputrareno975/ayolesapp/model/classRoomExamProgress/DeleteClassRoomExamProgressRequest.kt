package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.syahputrareno975.ayolesapp.model.BaseModel

class DeleteClassRoomExamProgressRequest:BaseModel {
    var ClassroomId : String = ""

    constructor()

    constructor(ClassroomId: String) {
        this.ClassroomId = ClassroomId
    }

    fun toSchema() : String {
        return " mutation {\n" +
                "classroom_exam_progress_delete(\n" +
                "classroom_id : \"${ClassroomId}\",\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "course_exam_id,\n" +
                "course_exam_answer_id\n" +
                " }\n" +
                "}"
    }
}