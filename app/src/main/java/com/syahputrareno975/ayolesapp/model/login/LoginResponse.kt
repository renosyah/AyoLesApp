package com.syahputrareno975.ayolesapp.model.login

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import com.syahputrareno975.ayolesapp.model.student.StudentModel
import java.io.Serializable

class LoginResponse : Serializable {
    @SerializedName("data")
    var Data : StudentLogin = StudentLogin()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class StudentLogin :Serializable {
        @SerializedName("student_login")
        var StudentLogin : StudentModel = StudentModel()
    }
}