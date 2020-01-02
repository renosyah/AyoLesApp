package com.syahputrareno975.ayolesapp.model.courseExamAnswer

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class CourseExamAnswerModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course_exam_id")
    var CourseExamId : String = ""

    @SerializedName("type_answer")
    var TypeAnswer : Int = 0

    @SerializedName("label")
    var Label : String = ""

    @SerializedName("text")
    var Text : String = ""

    @SerializedName("image_url")
    var ImageURL : String = ""

    var IsSelected = false

    constructor()

    constructor(Id: String, CourseExamId: String, TypeAnswer: Int, Label: String, Text: String, ImageURL: String) {
        this.Id = Id
        this.CourseExamId = CourseExamId
        this.TypeAnswer = TypeAnswer
        this.Label = Label
        this.Text = Text
        this.ImageURL = ImageURL
    }

    companion object {
        val TYPE_TEXT : Int = 0
        val TYPE_IMAGE : Int = 1
        val TYPE_TEXT_AND_IMAGE : Int = 2
    }
}