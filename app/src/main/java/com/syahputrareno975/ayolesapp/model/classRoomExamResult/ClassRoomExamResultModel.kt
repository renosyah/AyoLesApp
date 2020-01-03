package com.syahputrareno975.ayolesapp.model.classRoomExamResult

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.courseExamAnswer.CourseExamAnswerModel

class ClassRoomExamResultModel : BaseModel {

    @SerializedName("course_exam_id")
    var CourseExamID :String = ""

    @SerializedName("course_id")
    var CourseID :String = ""

    @SerializedName("classroom_id")
    var ClassRoomID :String = ""

    @SerializedName("student_answer_id")
    var StudentAnswerId :String = ""

    @SerializedName("valid_answer_id")
    var ValidAnswerID :String = ""

    @SerializedName("type_exam")
    var TypeExam :Int = 0

    @SerializedName("exam_index")
    var ExamIndex :Int = 0

    @SerializedName("text")
    var Text :String = ""

    @SerializedName("image_url")
    var ImageURL :String = ""

    @SerializedName("answers")
    var Answers : ArrayList<CourseExamAnswerModel> = ArrayList()

    constructor()

    constructor(CourseExamID: String, CourseID: String, ClassRoomID: String, StudentAnswerdI: String, ValidAnswerID: String, TypeExam: Int, ExamIndex: Int, Text: String, ImageURL: String, Answers: ArrayList<CourseExamAnswerModel>) {
        this.CourseExamID = CourseExamID
        this.CourseID = CourseID
        this.ClassRoomID = ClassRoomID
        this.StudentAnswerId = StudentAnswerId
        this.ValidAnswerID = ValidAnswerID
        this.TypeExam = TypeExam
        this.ExamIndex = ExamIndex
        this.Text = Text
        this.ImageURL = ImageURL
        this.Answers = Answers
    }
}