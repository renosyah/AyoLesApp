package com.syahputrareno975.ayolesapp.model.classRoom

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllClassRoomResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassRoomList = ClassRoomList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomList :Serializable {
        @SerializedName("classroom_list")
        var ClassRoomList : ArrayList<ClassRoomModel> = ArrayList<ClassRoomModel>()
    }
}