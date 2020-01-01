package com.syahputrareno975.ayolesapp.model.courseMaterialDetail

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseMaterial.CourseMaterialModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCourseMaterialDetailResponse : BaseModel {
    @SerializedName("data")
    var Data : CourseMaterialDetailList = CourseMaterialDetailList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseMaterialDetailList : Serializable {
        @SerializedName("course_material_detail_list")
        var CourseMaterialDetailList : ArrayList<CourseMaterialDetailModel> = ArrayList<CourseMaterialDetailModel>()
    }
}