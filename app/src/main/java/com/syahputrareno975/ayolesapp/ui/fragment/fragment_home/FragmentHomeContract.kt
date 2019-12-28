package com.syahputrareno975.ayolesapp.ui.fragment.fragment_home

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.banner.AllBannerRequest
import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse

class FragmentHomeContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for response
        fun onGetAllBanner(s : AllBannerResponse)
        fun onGetAllCategory(s : AllCategoryResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllBanner(r : AllBannerRequest)
        fun getAllCategory(r : AllCategoryRequest)
    }
}