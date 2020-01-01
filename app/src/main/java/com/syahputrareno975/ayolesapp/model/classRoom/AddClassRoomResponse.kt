package com.syahputrareno975.ayolesapp.model.classRoom

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AddClassRoomResponse : BaseModel {
    @SerializedName("data")
    var Data : AddClassRoomRegister = AddClassRoomRegister()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class AddClassRoomRegister :Serializable {
        @SerializedName("classroom_register")
        var ClassRoomRegister : ClassRoomModel = ClassRoomModel()
    }
}