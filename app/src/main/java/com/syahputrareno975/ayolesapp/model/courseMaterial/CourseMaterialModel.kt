package com.syahputrareno975.ayolesapp.model.courseMaterial

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class CourseMaterialModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course_id")
    var CourseId : String = ""

    @SerializedName("material_index")
    var MaterialIndex : Int = 0

    @SerializedName("title")
    var Title : String = ""

    var IsCompleted : Boolean = false

    constructor()

    constructor(Id: String, CourseId: String, MaterialIndex: Int, Title: String) {
        this.Id = Id
        this.CourseId = CourseId
        this.MaterialIndex = MaterialIndex
        this.Title = Title
    }


}