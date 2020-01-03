package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.syahputrareno975.ayolesapp.model.BaseModel

class AddClassRoomExamProgressRequest:BaseModel {
    var ClassroomId : String = ""
    var CourseExamId : String = ""
    var CourseExamAnswerId : String = ""

    constructor()

    constructor(ClassroomId: String, CourseExamId: String, CourseExamAnswerId: String) {
        this.ClassroomId = ClassroomId
        this.CourseExamId = CourseExamId
        this.CourseExamAnswerId = CourseExamAnswerId
    }

    fun toSchema() : String {
        return "mutation {\n" +
                "classroom_exam_progress_register(\n" +
                "classroom_id : \"${ClassroomId}\",\n" +
                "course_exam_id : \"${CourseExamId}\",\n" +
                "course_exam_answer_id : \"${CourseExamAnswerId}\"\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "classroom_id,\n" +
                "course_exam_id,\n" +
                "course_exam_answer_id\n" +
                " }\n" +
                "} "
    }

}