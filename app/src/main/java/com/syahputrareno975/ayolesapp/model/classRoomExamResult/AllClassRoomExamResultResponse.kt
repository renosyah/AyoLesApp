package com.syahputrareno975.ayolesapp.model.classRoomExamResult

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel

class AllClassRoomExamResultResponse:BaseModel {
    @SerializedName("data")
    var Data : ClassRoomExamResultList = ClassRoomExamResultList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomExamResultList :BaseModel {
        @SerializedName("classroom_exam_result_list")
        var classRoomExamResultModelList : ArrayList<ClassRoomExamResultModel> = ArrayList<ClassRoomExamResultModel>()
    }
}