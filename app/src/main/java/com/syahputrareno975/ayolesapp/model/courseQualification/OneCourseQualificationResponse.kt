package com.syahputrareno975.ayolesapp.model.courseQualification

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.CourseMaterialDetailModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class OneCourseQualificationResponse:BaseModel {
    @SerializedName("data")
    var Data : CourseQualificationDetail = CourseQualificationDetail()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseQualificationDetail:BaseModel {
        @SerializedName("course_qualification_detail")
        var CourseQualificationDetail : CourseQualificationModel = CourseQualificationModel()
    }
}