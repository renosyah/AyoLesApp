package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class ClassRoomExamProgressModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("classroom_id")
    var ClassroomId : String = ""

    @SerializedName("course_exam_id")
    var CourseExamId : String = ""

    @SerializedName("course_exam_answer_id")
    var CourseExamAnswerId : String = ""

    constructor()

    constructor(Id: String, ClassroomId: String, CourseExamId: String, CourseExamAnswerId: String) {
        this.Id = Id
        this.ClassroomId = ClassroomId
        this.CourseExamId = CourseExamId
        this.CourseExamAnswerId = CourseExamAnswerId
    }
}