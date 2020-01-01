package com.syahputrareno975.ayolesapp.model.classRoomProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllClassRoomProgressResponse : BaseModel {
    @SerializedName("data")
    var Data : ClassRoomProgressList = ClassRoomProgressList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomProgressList :BaseModel {
        @SerializedName("classroom_progress_list")
        var ClassRoomProgressList : ArrayList<ClassRoomProgressModel> = ArrayList<ClassRoomProgressModel>()
    }
}