package com.syahputrareno975.ayolesapp.model.courseMaterial

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCourseMaterialResponse : BaseModel {
    @SerializedName("data")
    var Data : CourseMaterialList = CourseMaterialList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CourseMaterialList : Serializable {
        @SerializedName("course_material_list")
        var CourseMaterialList : ArrayList<CourseMaterialModel> = ArrayList<CourseMaterialModel>()
    }
}