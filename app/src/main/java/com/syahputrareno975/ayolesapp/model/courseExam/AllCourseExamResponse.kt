package com.syahputrareno975.ayolesapp.model.courseExam

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseDetail.CourseDetailModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCourseExamResponse : BaseModel {
    @SerializedName("data")
    var Data : CourseExamList = CourseExamList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseExamList : Serializable {
        @SerializedName("course_exam_list")
        var CourseExamList : ArrayList<CourseExamModel> = ArrayList<CourseExamModel>()
    }
}