package com.syahputrareno975.ayolesapp.model.student

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class UpdateRequest : BaseModel {
    var Id : String = ""
    var Name : String = ""
    var Email : String = ""
    var Password : String = ""

    constructor(Id: String, Name: String, Email: String, Password: String) {
        this.Id = Id
        this.Name = Name
        this.Email = Email
        this.Password = Password
    }

    fun toSchema() : String {
        return "mutation {student_update(id : \"${Id}\",name: \"${Name}\",email:\"$Email\",password:\"$Password\"){id,name,email}}"
    }
}