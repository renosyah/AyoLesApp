package com.syahputrareno975.ayolesapp.model.category

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CategoryModel : Serializable {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("name")
    var Name : String = ""

    @SerializedName("image_url")
    var ImageUrl : String = ""


    constructor()

    constructor(Id: String, Name: String, ImageUrl: String) {
        this.Id = Id
        this.Name = Name
        this.ImageUrl = ImageUrl
    }


}