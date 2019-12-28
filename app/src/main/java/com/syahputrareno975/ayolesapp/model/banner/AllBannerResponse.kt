package com.syahputrareno975.ayolesapp.model.banner

import com.google.gson.annotations.SerializedName
import com.syahputrareno975.ayolesapp.model.queryModel.ErrorModel
import java.io.Serializable

class AllBannerResponse : Serializable {
    @SerializedName("data")
    var Data : BannerList = BannerList()

    @SerializedName("errors")
    var Errors = ArrayList<ErrorModel>()

    class BannerList :Serializable {
        @SerializedName("banner_list")
        var BannerList : ArrayList<BannerModel> = ArrayList<BannerModel>()
    }

}