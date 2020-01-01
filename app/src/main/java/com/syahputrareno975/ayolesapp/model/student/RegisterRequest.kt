package com.syahputrareno975.ayolesapp.model.student


import com.syahputrareno975.ayolesapp.model.BaseModel

class RegisterRequest : BaseModel {

    var Name : String = ""
    var Email : String = ""
    var Password : String = ""

    constructor(Name: String, Email: String, Password: String) {
        this.Name = Name
        this.Email = Email
        this.Password = Password
    }

    fun toSchema() : String {
        return "mutation {student_register(name: \"${Name}\",email:\"$Email\",password:\"$Password\"){id,name,email}}"
    }
}