package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AddClassRoomExamProgressResponse:BaseModel {
    @SerializedName("data")
    var Data : ClassRoomExamProgressRegister = ClassRoomExamProgressRegister()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomExamProgressRegister : Serializable {
        @SerializedName("classroom_exam_progress_register")
        var ClassRoomExamProgressRegister : ClassRoomExamProgressModel = ClassRoomExamProgressModel()
    }
}