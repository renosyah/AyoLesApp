package com.syahputrareno975.ayolesapp.di.component

import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    // add for each new activity
    fun inject(loginActivity: LoginActivity)
}