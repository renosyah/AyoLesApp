package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class DeleteClassRoomExamProgressResponse:BaseModel {
    @SerializedName("data")
    var Data : ClassRoomExamProgressDelete = ClassRoomExamProgressDelete()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomExamProgressDelete : Serializable {
        @SerializedName("classroom_exam_progress_delete")
        var ClassRoomExamProgressDelete : ClassRoomExamProgressModel = ClassRoomExamProgressModel()
    }
}