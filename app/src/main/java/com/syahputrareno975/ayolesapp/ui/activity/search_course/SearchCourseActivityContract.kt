package com.syahputrareno975.ayolesapp.ui.activity.search_course

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse

class SearchCourseActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllCourses(s : AllCourseResponse)
        fun onGetAllCategory(s : AllCategoryResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllCourses(r : AllCourseRequest)
        fun getAllCategory(r : AllCategoryRequest)
    }
}