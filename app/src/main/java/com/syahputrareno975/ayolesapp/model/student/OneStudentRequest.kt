package com.syahputrareno975.ayolesapp.model.student

import com.syahputrareno975.ayolesapp.model.BaseModel

class OneStudentRequest : BaseModel {
    var Id : String = ""

    constructor(Id: String) {
        this.Id = Id
    }
    
    fun toSchema() : String {
        return "query {\n" +
                "student_detail(\n" +
                "id: \"${Id}\"\n" +
                ")\n" +
                "{\n" +
                "id,\n" +
                "name,\n" +
                "nis\n" +
                "}\n" +
                "}"
    }
}