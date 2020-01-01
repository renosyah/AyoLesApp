package com.syahputrareno975.ayolesapp.model.courseDetail

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class CourseDetailModel : BaseModel {

    @SerializedName("id")
    var Id  : String = ""

    @SerializedName("course_id")
    var CourseId : String = ""

    @SerializedName("overview_text")
    var OverviewText : String = ""

    @SerializedName("description_text")
    var DescriptionText : String = ""

    @SerializedName("image_url")
    var ImageUrl : String = ""

    constructor()

    constructor(Id: String, CourseId: String, OverviewText: String, DescriptionText: String, ImageUrl: String) {
        this.Id = Id
        this.CourseId = CourseId
        this.OverviewText = OverviewText
        this.DescriptionText = DescriptionText
        this.ImageUrl = ImageUrl
    }
}