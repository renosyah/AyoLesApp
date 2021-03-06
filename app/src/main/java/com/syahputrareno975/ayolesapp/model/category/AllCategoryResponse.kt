package com.syahputrareno975.ayolesapp.model.category

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.BaseModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCategoryResponse : BaseModel {
    @SerializedName("data")
    var Data : CategoryList = CategoryList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CategoryList :Serializable {
        @SerializedName("category_list")
        var CategoryList : ArrayList<CategoryModel> = ArrayList<CategoryModel>()
    }
}