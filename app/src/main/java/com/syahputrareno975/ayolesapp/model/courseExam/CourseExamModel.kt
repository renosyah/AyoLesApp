package com.syahputrareno975.ayolesapp.model.courseExam

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseExamAnswer.CourseExamAnswerModel

class CourseExamModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course_id")
    var CourseId  : String = ""

    @SerializedName("type_exam")
    var TypeExam  : Int = 0

    @SerializedName("exam_index")
    var ExamIndex : Int = 0

    @SerializedName("text")
    var Text: String = ""

    @SerializedName("image_url")
    var ImageURL: String = ""

    @SerializedName("answers")
    var Answers : ArrayList<CourseExamAnswerModel> = ArrayList()


    var IsSubmited = false

    constructor()

    constructor(Id: String, CourseId: String, TypeExam: Int, ExamIndex: Int, Text: String, ImageURL: String, Answers: ArrayList<CourseExamAnswerModel>) {
        this.Id = Id
        this.CourseId = CourseId
        this.TypeExam = TypeExam
        this.ExamIndex = ExamIndex
        this.Text = Text
        this.ImageURL = ImageURL
        this.Answers = Answers
    }

    fun isAnswered() : Boolean {
        for (i in this.Answers){
            if (i.IsSelected){
                return true
            }
        }
        return false
    }

    fun getAnswer() : CourseExamAnswerModel? {
        for (i in this.Answers){
            if (i.IsSelected){
                return i
            }
        }
        return null
    }

    companion object {
        val TYPE_TEXT : Int = 0
        val TYPE_IMAGE : Int = 1
        val TYPE_TEXT_AND_IMAGE : Int = 2
    }

}