package com.syahputrareno975.ayolesapp.model.login

import java.io.Serializable

class LoginRequest : Serializable {

    var Email : String = ""
    var Password : String = ""

    constructor(email: String, password: String) {
        this.Email = email
        this.Password = password
    }


    fun toSchema() : String {
        return "query {student_login(email:\"$Email\",password:\"$Password\"){id,name,email}}"
    }
}