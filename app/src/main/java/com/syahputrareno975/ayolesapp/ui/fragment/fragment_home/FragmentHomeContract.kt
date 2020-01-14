package com.syahputrareno975.ayolesapp.ui.fragment.fragment_home

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.banner.AllBannerRequest
import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.OneCategoryResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse

class FragmentHomeContract {
    interface View: BaseContract.View {

        // add more for response
        fun onGetAllBanner(s : AllBannerResponse)
        fun showProgressOnGetAllBanner(show: Boolean)
        fun showErrorOnGetAllBanner(error: String)

        fun onGetAllCategory(s : AllCategoryResponse)
        fun showProgressOnGetAllCategory(show: Boolean)
        fun showErrorOnGetAllCategory(error: String)

        fun onGetAllCategoryForCourse(s : AllCategoryResponse)
        fun showProgressOnGetAllCategoryForCourse(show: Boolean)
        fun showErrorOnGetAllCategoryForCourse(error: String)

        fun onGetAllCourses(s : AllCourseResponse,position : Int)
        fun showProgressOnGetAllCourses(show: Boolean)
        fun showErrorOnGetAllCourses(error: String)

    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllBanner(r : AllBannerRequest,enableLoading : Boolean)
        fun getAllCategory(r : AllCategoryRequest,enableLoading : Boolean)
        fun getAllCategoryForCourse(r : AllCategoryRequest,enableLoading : Boolean)
        fun getAllCourses(r : AllCourseRequest,position : Int,enableLoading : Boolean)
    }
}