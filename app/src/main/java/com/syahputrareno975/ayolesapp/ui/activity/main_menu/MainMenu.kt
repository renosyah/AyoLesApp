package com.syahputrareno975.ayolesapp.ui.activity.main_menu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClass
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_home.FragmentHome
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile.FragmentProfile
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    lateinit var context :Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initWidget()
    }

    fun initWidget(){
        this.context = this@MainMenu
        menu_bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.menu_home -> {
                    changeFragment(FragmentHome.newInstance())
                }
                R.id.menu_class -> {
                    changeFragment(FragmentClass.newInstance())
                }
                R.id.menu_profile -> {
                    changeFragment(FragmentProfile.newInstance())
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        changeFragment(FragmentHome.newInstance())
    }

    private fun changeFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_fragment_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
