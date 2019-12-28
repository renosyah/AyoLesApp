package com.syahputrareno975.ayolesapp.di.module

import android.app.Application
import com.syahputrareno975.ayolesapp.BaseApp
import com.syahputrareno975.ayolesapp.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}