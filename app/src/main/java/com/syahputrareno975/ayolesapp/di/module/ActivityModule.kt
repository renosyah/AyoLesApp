package com.syahputrareno975.ayolesapp.di.module

import android.app.Activity
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity : Activity) {
    @Provides
    fun provideActivity() : Activity {
        return activity
    }

    @Provides
    fun provideApiService(): RetrofitService {
        return RetrofitService.create()
    }

    // add for each new activity
    @Provides
    fun provideLoginActivityPresenter(): LoginActivityContract.Presenter {
        return LoginActivityPresenter()
    }

    @Provides
    fun provideSearchCourseActivityPresenter(): SearchCourseActivityContract.Presenter {
        return SearchCourseActivityPresenter()
    }

    @Provides
    fun provideDetailCourseActivityPresenter(): DetailCourseActivityContract.Presenter {
        return DetailCourseActivityPresenter()
    }
}