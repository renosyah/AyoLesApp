package com.syahputrareno975.ayolesapp.di.module

import android.app.Activity
import androidx.fragment.app.Fragment
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClassContract
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClassPresenter
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_home.FragmentHomeContract
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_home.FragmentHomePresenter
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile.FragmentProfileContract
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile.FragmentProfilePresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private var fragment: Fragment) {

    @Provides
    fun provideFragment() : Fragment {
        return fragment
    }
    @Provides
    fun provideApiService(): RetrofitService {
        return RetrofitService.create()
    }

    @Provides
    fun provideHomePresenter(): FragmentHomeContract.Presenter {
        return FragmentHomePresenter()
    }

    @Provides
    fun provideClassPresenter(): FragmentClassContract.Presenter {
        return FragmentClassPresenter()
    }

    @Provides
    fun provideProfilePresenter(): FragmentProfileContract.Presenter {
        return FragmentProfilePresenter()
    }
}