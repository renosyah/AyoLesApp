package com.syahputrareno975.ayolesapp.model.student

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class UpdateResponse : BaseModel {
    @SerializedName("data")
    var Data : StudentUpdate = StudentUpdate()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class StudentUpdate : Serializable {
        @SerializedName("student_update")
        var StudentUpdate  : StudentModel = StudentModel()
    }
}