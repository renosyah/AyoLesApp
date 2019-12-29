package com.syahputrareno975.ayolesapp.model.course

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCourseResponse : Serializable {
    @SerializedName("data")
    var Data : CourseList = CourseList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseList :Serializable {
        @SerializedName("course_list")
        var CourseList : ArrayList<CourseModel> = ArrayList<CourseModel>()
    }
}