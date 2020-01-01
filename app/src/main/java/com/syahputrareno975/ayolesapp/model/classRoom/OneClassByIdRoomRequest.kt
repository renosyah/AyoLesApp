package com.syahputrareno975.ayolesapp.model.classRoom

import com.syahputrareno975.ayolesapp.model.BaseModel

class OneClassByIdRoomRequest : BaseModel {
    var CourseId : String = ""
    var StudentId : String = ""

    constructor(CourseId: String, StudentId: String) {
        this.CourseId = CourseId
        this.StudentId = StudentId
    }

    fun toSchema() : String {
        return "query {\n" +
                "classroom_detail_by_id(\n" +
                "course_id : \"${CourseId}\",\n" +
                "student_id : \"${StudentId}\"\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "course {\n" +
                "id,\n" +
                "course_name,\n" +
                "image_url,\n" +
                "teacher {id, name, email } ,\n" +
                "category {id, name, image_url},\n" +
                "course_details { id,course_id , overview_text, description_text,image_url }\n" +
                "},\n" +
                "student_id\n" +
                "}\n" +
                "}"
    }
}