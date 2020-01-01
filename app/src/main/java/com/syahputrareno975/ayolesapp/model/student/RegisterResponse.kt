package com.syahputrareno975.ayolesapp.model.student

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class RegisterResponse : BaseModel {
    @SerializedName("data")
    var Data : StudentRegister =
        StudentRegister()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class StudentRegister : Serializable {
        @SerializedName("student_register")
        var StudentRegister : StudentModel = StudentModel()
    }
}