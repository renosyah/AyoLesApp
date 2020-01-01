package com.syahputrareno975.ayolesapp.model.classRoomProgress

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class ClassRoomProgressModel: BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("classroom_id")
    var ClassroomId : String = ""

    @SerializedName("course_material_id")
    var CourseMaterialId : String = ""

    constructor()

    constructor(Id: String, ClassroomId: String, CourseMaterialId: String) {
        this.Id = Id
        this.ClassroomId = ClassroomId
        this.CourseMaterialId = CourseMaterialId
    }

}