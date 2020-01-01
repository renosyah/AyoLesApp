package com.syahputrareno975.ayolesapp.model.classRoomProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel

class AddClassRoomProgressResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassRoomProgressRegister = ClassRoomProgressRegister()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomProgressRegister : BaseModel {
        @SerializedName("classroom_progress_register")
        var ClassRoomProgressRegister : ClassRoomProgressModel = ClassRoomProgressModel()
    }
}