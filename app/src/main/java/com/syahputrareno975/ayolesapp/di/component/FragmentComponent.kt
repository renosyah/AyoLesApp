package com.syahputrareno975.ayolesapp.di.component

import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClass
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_home.FragmentHome
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile.FragmentProfile
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    // add for each new fragment
    fun inject(f : FragmentHome)
    fun inject(f : FragmentClass)
    fun inject(f : FragmentProfile)
}