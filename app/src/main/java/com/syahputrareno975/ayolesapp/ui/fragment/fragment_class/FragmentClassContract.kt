package com.syahputrareno975.ayolesapp.ui.fragment.fragment_class

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse

class FragmentClassContract {
    interface View: BaseContract.View {

        // add more for request
        fun onGetAllClass(s : AllClassRoomResponse)
        fun showProgressOnGetAllClass(show: Boolean)
        fun showErrorOnGetAllClass(error: String)

        fun onGetAllCategory(s : AllCategoryResponse)
        fun showProgressOnGetAllCategory(show: Boolean)
        fun showErrorOnGetAllCategory(error: String)

    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllClass(r : AllClassRoomRequest,enableLoading : Boolean)
        fun getAllCategory(r : AllCategoryRequest,enableLoading : Boolean)
    }
}