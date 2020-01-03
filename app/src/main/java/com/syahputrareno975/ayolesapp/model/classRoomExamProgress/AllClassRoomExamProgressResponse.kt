package com.syahputrareno975.ayolesapp.model.classRoomExamProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.classRoomProgress.ClassRoomProgressModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel

class AllClassRoomExamProgressResponse:BaseModel {
    @SerializedName("data")
    var Data : ClassRoomExamProgressList = ClassRoomExamProgressList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomExamProgressList :BaseModel {
        @SerializedName("classroom_exam_progress_list")
        var ClassRoomExamProgressList : ArrayList<ClassRoomExamProgressModel> = ArrayList<ClassRoomExamProgressModel>()
    }
}