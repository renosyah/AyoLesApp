package com.syahputrareno975.ayolesapp.model.course

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.courseDetail.CourseDetailModel
import com.syahputrareno975.ayolesapp.model.teacher.TeacherModel
import java.io.Serializable

class CourseModel : Serializable {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("course_name")
    var CourseName : String = ""

    @SerializedName("image_url")
    var ImageUrl : String = ""

    @SerializedName("teacher")
    var Teacher : TeacherModel = TeacherModel()

    @SerializedName("category")
    var Category :CategoryModel = CategoryModel()

    @SerializedName("course_details")
    var CourseDetails :ArrayList<CourseDetailModel> = ArrayList()

    constructor()

    constructor(Id: String, CourseName: String, ImageUrl: String, Teacher: TeacherModel, Category: CategoryModel, CourseDetails: ArrayList<CourseDetailModel>) {
        this.Id = Id
        this.CourseName = CourseName
        this.ImageUrl = ImageUrl
        this.Teacher = Teacher
        this.Category = Category
        this.CourseDetails = CourseDetails
    }


}