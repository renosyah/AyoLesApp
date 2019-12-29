package com.syahputrareno975.ayolesapp.di.component

import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivity
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivity
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    // add for each new activity
    fun inject(loginActivity: LoginActivity)
    fun inject(searchCourseActivity: SearchCourseActivity)
    fun inject(detailCourseActivity: DetailCourseActivity)
}