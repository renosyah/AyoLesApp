package com.syahputrareno975.ayolesapp.di.component

import com.syahputrareno975.ayolesapp.BaseApp
import com.syahputrareno975.ayolesapp.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApp)
}