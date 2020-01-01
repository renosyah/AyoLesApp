package com.syahputrareno975.ayolesapp.model.course

import com.syahputrareno975.ayolesapp.model.BaseModel

class VerticalCourseModel : BaseModel {
    var Id : String = ""
    var Title : String = ""
    var Courses : ArrayList<CourseModel> = ArrayList()


    constructor(Id: String, Title: String, Courses: ArrayList<CourseModel>) {
        this.Id = Id
        this.Title = Title
        this.Courses = Courses
    }
}