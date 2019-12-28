package com.syahputrareno975.ayolesapp.di.module

import android.app.Activity
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivityPresenter
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
    
}