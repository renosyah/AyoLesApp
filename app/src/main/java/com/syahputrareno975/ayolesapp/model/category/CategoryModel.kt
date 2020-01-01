package com.syahputrareno975.ayolesapp.model.category

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class CategoryModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("name")
    var Name : String = ""

    @SerializedName("image_url")
    var ImageUrl : String = ""

    var IsClick = false

    constructor()

    constructor(Id: String, Name: String, ImageUrl: String) {
        this.Id = Id
        this.Name = Name
        this.ImageUrl = ImageUrl
    }


}