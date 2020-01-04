package com.syahputrareno975.ayolesapp.model.courseQualification

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import java.util.logging.Level

class CourseQualificationModel : BaseModel {

    @SerializedName("id")
    var Id :String = ""

    @SerializedName("course_id")
    var CourseId :String = ""

    @SerializedName("course_level")
    var CourseLevel :String = ""

    @SerializedName("min_score")
    var MinScore  :Int = 0

    @SerializedName("course_material_total")
    var CourseMaterialTotal :Int = 0

    @SerializedName("course_exam_total")
    var CourseExamTotal  :Int = 0

    constructor()

    constructor(Id: String, CourseId: String, CourseLevel: String, MinScore: Int, CourseMaterialTotal: Int, CourseExamTotal: Int) {
        this.Id = Id
        this.CourseId = CourseId
        this.CourseLevel = CourseLevel
        this.MinScore = MinScore
        this.CourseMaterialTotal = CourseMaterialTotal
        this.CourseExamTotal = CourseExamTotal
    }

    override fun toString(): String {
        var text = ""
        text += "Level : $CourseLevel\n"
        text += "Min Score : $MinScore\n"
        text += "Total Material : $CourseMaterialTotal\n"
        text += "Exam Total : $CourseExamTotal\n"
        return text
    }

}