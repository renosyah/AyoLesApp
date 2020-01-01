package com.syahputrareno975.ayolesapp.model.category

import com.syahputrareno975.ayolesapp.model.BaseModel

class OneCategoryRequest : BaseModel {
    var Id : String = ""

    constructor(Id: String) {
        this.Id = Id
    }

    fun toSchema() : String {
        return "query { category_detail( id:\"${Id}\" ) { id, name, image_url }}"
    }
}