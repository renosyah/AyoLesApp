package com.syahputrareno975.ayolesapp.model.courseExamAnswer

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseExam.CourseExamModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCourseExamAnswerResponse : BaseModel {
    @SerializedName("data")
    var Data : CourseExamAnswerList = CourseExamAnswerList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseExamAnswerList : Serializable {
        @SerializedName("course_exam_answer_list")
        var CourseExamAnswerList : ArrayList<CourseExamAnswerModel> = ArrayList<CourseExamAnswerModel>()
    }
}