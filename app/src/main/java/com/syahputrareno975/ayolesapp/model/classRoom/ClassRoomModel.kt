package com.syahputrareno975.ayolesapp.model.classRoom

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.course.CourseModel


class ClassRoomModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course")
    var Course  : CourseModel = CourseModel()

    @SerializedName("student_id")
    var StudentID : String = ""

    var isCompleted = false

    constructor()

    constructor(Id: String, Course: CourseModel, StudentID: String) {
        this.Id = Id
        this.Course = Course
        this.StudentID = StudentID
    }


}