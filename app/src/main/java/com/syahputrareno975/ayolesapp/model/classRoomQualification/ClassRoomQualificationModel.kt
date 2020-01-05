package com.syahputrareno975.ayolesapp.model.classRoomQualification

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseQualification.CourseQualificationModel

class ClassRoomQualificationModel:BaseModel {

    @SerializedName("classroom_id")
    var ClassRoomID: String = ""

    @SerializedName("course_qualification")
    var CourseQualification : CourseQualificationModel = CourseQualificationModel()

    @SerializedName("total_score")
    var TotalScore: Int = 0

    @SerializedName("status")
    var Status:Int = 0

    constructor()

    constructor(
        ClassRoomID: String,
        CourseQualification: CourseQualificationModel,
        TotalScore: Int,
        Status: Int
    ) {
        this.ClassRoomID = ClassRoomID
        this.CourseQualification = CourseQualification
        this.TotalScore = TotalScore
        this.Status = Status
    }

    companion object {
        val STATUS_NO_PROGRESS   = 0
        val STATUS_PASS_EXAM     = 1
        val STATUS_NOT_PASS_EXAM = 2
    }

}