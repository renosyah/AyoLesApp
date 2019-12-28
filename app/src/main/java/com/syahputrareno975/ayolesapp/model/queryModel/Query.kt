package com.syahputrareno975.ayolesapp.model.queryModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Query : Serializable {

    @SerializedName("query")
    var query : String = ""

    constructor(query: String) {
        this.query = query
    }
}