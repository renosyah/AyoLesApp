package com.syahputrareno975.ayolesapp.model.student

import com.syahputrareno975.ayolesapp.model.BaseModel

class LoginRequest : BaseModel {

    var Nis : String = ""
    var Password : String = ""

    constructor(nis: String,password : String) {
        this.Nis = nis
        this.Password = password
    }


    fun toSchema() : String {
        return "query {student_login(nis:\"$Nis\",password:\"$Password\",){id,name,nis}}"
    }
}