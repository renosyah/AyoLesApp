package com.syahputrareno975.ayolesapp.model.queryModel

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel

class Query : BaseModel {

    @SerializedName("query")
    var query : String = ""

    constructor(query: String) {
        this.query = query
    }
}