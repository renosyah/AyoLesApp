package com.syahputrareno975.ayolesapp.model.classRoom

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import java.io.Serializable

class ClassRoomModel :Serializable {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course")
    var Course  : CourseModel = CourseModel()

    @SerializedName("student_id")
    var StudentID : String = ""

    constructor()

    constructor(Id: String, Course: CourseModel, StudentID: String) {
        this.Id = Id
        this.Course = Course
        this.StudentID = StudentID
    }


}