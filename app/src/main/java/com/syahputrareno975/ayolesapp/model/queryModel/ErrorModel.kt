package com.syahputrareno975.ayolesapp.model.queryModel

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class ErrorModel : BaseModel {

    @SerializedName("message")
    var Message = ""

    @SerializedName("location")
    var Location = ArrayList<ErrorLoationModel>()

    class ErrorLoationModel {

        @SerializedName("line")
        var Line = 0

        @SerializedName("column")
        var Column = 0
    }
}