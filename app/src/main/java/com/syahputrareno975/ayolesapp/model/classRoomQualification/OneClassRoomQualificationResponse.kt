package com.syahputrareno975.ayolesapp.model.classRoomQualification

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.classRoomProgress.ClassRoomProgressModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel

class OneClassRoomQualificationResponse:BaseModel {
    @SerializedName("data")
    var Data : ClassRoomQualificationDetail = ClassRoomQualificationDetail()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class ClassRoomQualificationDetail :BaseModel {
        @SerializedName("class_qualification_detail")
        var ClassRoomQualificationDetail : ClassRoomQualificationModel = ClassRoomQualificationModel()
    }
}
