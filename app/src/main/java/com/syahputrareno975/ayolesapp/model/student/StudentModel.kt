package com.syahputrareno975.ayolesapp.model.student

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import java.io.Serializable

class StudentModel : BaseModel {

    @SerializedName("id")
    var Id : String = ""

    @SerializedName("name")
    var Name : String = ""

    @SerializedName("nis")
    var Nis : String = ""

    @SerializedName("password")
    var Password : String = ""

    constructor()

    constructor(Id: String, Name: String, Nis: String, Password: String) {
        this.Id = Id
        this.Name = Name
        this.Nis = Nis
        this.Password = Password
    }


}