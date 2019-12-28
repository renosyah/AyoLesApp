package com.syahputrareno975.ayolesapp

import android.app.Application
import com.syahputrareno975.ayolesapp.di.component.ApplicationComponent
import com.syahputrareno975.ayolesapp.di.component.DaggerApplicationComponent
import com.syahputrareno975.ayolesapp.di.module.ApplicationModule

class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()

        if (BuildConfig.DEBUG){

        }
    }

    fun setup(){
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance : BaseApp private set
    }
}