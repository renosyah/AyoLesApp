package com.syahputrareno975.ayolesapp.model.student

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class LoginResponse : BaseModel {
    @SerializedName("data")
    var Data : StudentLogin =
        StudentLogin()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class StudentLogin :Serializable {
        @SerializedName("student_login")
        var StudentLogin : StudentModel = StudentModel()
    }
}