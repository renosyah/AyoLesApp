package com.syahputrareno975.ayolesapp.model.student

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class OneStudentResponse : BaseModel {
    @SerializedName("data")
    var Data : StudentDetail  = StudentDetail ()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class StudentDetail :Serializable {
        @SerializedName("student_detail")
        var StudentDetail  : StudentModel = StudentModel()
    }
}