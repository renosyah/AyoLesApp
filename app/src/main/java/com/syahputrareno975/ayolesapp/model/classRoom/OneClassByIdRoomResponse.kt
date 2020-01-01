package com.syahputrareno975.ayolesapp.model.classRoom

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class OneClassByIdRoomResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassroomDetailById  = ClassroomDetailById ()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassroomDetailById :Serializable {
        @SerializedName("classroom_detail_by_id")
        var ClassroomDetailById  : ClassRoomModel = ClassRoomModel()
    }
}