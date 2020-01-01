package com.syahputrareno975.ayolesapp.model.courseMaterialDetail

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class CourseMaterialDetailModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course_material_id")
    var CourseMaterialId : String = ""

    @SerializedName("position")
    var Position : Int = 0

    @SerializedName("title")
    var Title : String = ""

    @SerializedName("type_material")
    var TypeMaterial : Int = 0

    @SerializedName("content")
    var Content : String = ""

    @SerializedName("image_url")
    var ImageUrl : String = ""

    constructor()

    constructor(Id: String, CourseMaterialId: String, Position: Int, Title: String, TypeMaterial: Int, Content: String, ImageUrl: String) {
        this.Id = Id
        this.CourseMaterialId = CourseMaterialId
        this.Position = Position
        this.Title = Title
        this.TypeMaterial = TypeMaterial
        this.Content = Content
        this.ImageUrl = ImageUrl
    }

}