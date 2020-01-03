package com.syahputrareno975.ayolesapp.di.module

import android.app.Activity
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.exam_classroom.ExamClassRoomActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.exam_classroom.ExamClassRoomActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.exam_result.ExamResultActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.exam_result.ExamResultActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.material_classroom.MaterialClassRoomActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.material_classroom.MaterialClassRoomActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.material_detail.MaterialDetailClassRoomActivity
import com.syahputrareno975.ayolesapp.ui.activity.material_detail.MaterialDetailClassRoomActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.material_detail.MaterialDetailClassRoomActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.register.RegisterActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.register.RegisterActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivityPresenter
import com.syahputrareno975.ayolesapp.ui.activity.update_profile.UpdateProfileActivityContract
import com.syahputrareno975.ayolesapp.ui.activity.update_profile.UpdateProfileActivityPresenter
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

    @Provides
    fun provideMaterialClassRoomActivityPresenter(): MaterialClassRoomActivityContract.Presenter {
        return MaterialClassRoomActivityPresenter()
    }

    @Provides
    fun provideRegisterActivityPresenter(): RegisterActivityContract.Presenter {
        return RegisterActivityPresenter()
    }

    @Provides
    fun provideUpdateProfileActivityPresenter(): UpdateProfileActivityContract.Presenter {
        return  UpdateProfileActivityPresenter()
    }

    @Provides
    fun provideMaterialDetailClassRoomActivityPresenter(): MaterialDetailClassRoomActivityContract.Presenter {
        return  MaterialDetailClassRoomActivityPresenter()
    }

    @Provides
    fun provideExamClassRoomActivityPresenter(): ExamClassRoomActivityContract.Presenter {
        return  ExamClassRoomActivityPresenter()
    }

    @Provides
    fun provideExamResultActivityPresenter(): ExamResultActivityContract.Presenter {
        return ExamResultActivityPresenter()
    }
}