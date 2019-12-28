package com.syahputrareno975.ayolesapp.model.category

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.banner.BannerModel
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllCategoryResponse : Serializable {
    @SerializedName("data")
    var Data : CategoryList = CategoryList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class CategoryList :Serializable {
        @SerializedName("category_list")
        var CategoryList : ArrayList<CategoryModel> = ArrayList<CategoryModel>()
    }
}