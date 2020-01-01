package com.syahputrareno975.ayolesapp.model.courseDetail

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCourseDetailResponse  : BaseModel {
    @SerializedName("data")
    var Data : CourseDetailList = CourseDetailList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseDetailList :Serializable {
        @SerializedName("course_detail_list")
        var CourseDetailList : ArrayList<CourseDetailModel> = ArrayList<CourseDetailModel>()
    }
}