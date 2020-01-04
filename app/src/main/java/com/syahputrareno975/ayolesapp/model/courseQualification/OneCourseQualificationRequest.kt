package com.syahputrareno975.ayolesapp.model.courseQualification

import com.google.gson.annotations.SerializedName

class OneCourseQualificationRequest {
    var Id :String = ""
    var CourseId :String = ""

    constructor(Id: String, CourseId: String) {
        this.Id = Id
        this.CourseId = CourseId
    }

    fun toSchema() : String {
        return "query {\n" +
                "course_qualification_detail(\n" +
                "id: \"${Id}\",\n" +
                "course_id: \"${CourseId}\"\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "course_id,\n" +
                "course_level,\n" +
                "min_score,\n" +
                "course_material_total,\n" +
                "course_exam_total\n" +
                "}\n" +
                "} "
    }
}