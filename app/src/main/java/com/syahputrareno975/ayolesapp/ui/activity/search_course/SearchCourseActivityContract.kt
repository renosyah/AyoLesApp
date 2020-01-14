package com.syahputrareno975.ayolesapp.ui.activity.search_course

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse

class SearchCourseActivityContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetAllCourses(s : AllCourseResponse)
        fun showProgressOnGetAllCourses(show: Boolean)
        fun showErrorOnGetAllCourses(error: String)

        fun onGetAllCategory(s : AllCategoryResponse)
        fun showProgressOnGetAllCategory(show: Boolean)
        fun showErrorOnGetAllCategory(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllCourses(r : AllCourseRequest,enableLoading : Boolean)
        fun getAllCategory(r : AllCategoryRequest,enableLoading : Boolean)
    }
}