package com.syahputrareno975.ayolesapp.model.banner

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BannerModel : Serializable {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("title")
    var Title : String = ""

    @SerializedName("content")
    var Content : String = ""

    @SerializedName("image_url")
    var ImageUrl : String = ""

    constructor()

    constructor(Id: String, Title: String, Content: String, ImageUrl: String) {
        this.Id = Id
        this.Title = Title
        this.Content = Content
        this.ImageUrl = ImageUrl
    }


}