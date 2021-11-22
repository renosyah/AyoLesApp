package com.syahputrareno975.ayolesapp.model.student


import com.syahputrareno975.ayolesapp.model.BaseModel

class RegisterRequest : BaseModel {

    var Name : String = ""
    var Nis : String = ""
    var Password : String = ""

    constructor(Name: String, Nis: String, Password: String) {
        this.Name = Name
        this.Nis = Nis
        this.Password = Password
    }

    fun toSchema() : String {
        return "mutation {student_register(name: \"${Name}\",nis:\"$Nis\",password:\"$Password\"){id,name,nis}}"
    }
}