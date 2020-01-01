package com.syahputrareno975.ayolesapp.model.category

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class OneCategoryResponse : BaseModel {
    @SerializedName("data")
    var Data : CategoryDetail = CategoryDetail()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CategoryDetail :Serializable {
        @SerializedName("category_detail")
        var CategoryDetail : CategoryModel = CategoryModel()
    }
}