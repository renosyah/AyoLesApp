package com.syahputrareno975.ayolesapp.ui.activity.exam_classroom

import com.syahputrareno975.ayolesapp.base.BaseContract
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamRequest
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamResponse

class ExamClassRoomActivityContract {
    interface View: BaseContract.View {

        // mandatory response
        fun showProgress(show: Boolean)
        fun showError(error: String)

        // add more for request
        fun onGetAllExam(s : AllCourseExamResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {

        // add for request
        fun getAllExam(r : AllCourseExamRequest)
    }
}