package com.syahputrareno975.ayolesapp.ui.activity.login

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.login.LoginRequest
import com.syahputrareno975.ayolesapp.model.login.LoginResponse
import com.syahputrareno975.ayolesapp.ui.activity.main_menu.MainMenu
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(),LoginActivityContract.View {

    @Inject
    lateinit var presenter: LoginActivityContract.Presenter
    lateinit var context: Context
    var isLanding = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initWidget()
    }

    fun initWidget() {
        context = this@LoginActivity

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        open_login_button.setOnClickListener {
            openLoginForm()
        }

        login_button.setOnClickListener {
            login()
        }

        closeLoginForm()

        if (SerializableSave(context,SerializableSave.userDataFileSessionName).load() != null){
            startActivity(Intent(context,MainMenu::class.java))
            finish()
        }
    }

    fun initDisplay(){
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params = main_login_layout.layoutParams
        params.height = size.y
    }

    fun login(){
        error_login_message.visibility = View.GONE
        if (email_edittext.text.toString().isEmpty() || password_edittext.text.toString().isEmpty()){
            error_login_message.visibility = View.VISIBLE
            error_login_message.text = "Please fill all form!"
            return
        }
        presenter.login(LoginRequest(email_edittext.text.toString(),password_edittext.text.toString()))
        email_edittext.setText("")
        password_edittext.setText("")
    }

    fun openLoginForm(){
        isLanding = false
        initDisplay()
        layout_login.visibility = View.VISIBLE
        layout_landing.visibility = View.GONE
        layout_loading.visibility  = View.GONE
        error_login_message.visibility = View.GONE
    }

    fun closeLoginForm(){
        isLanding = true
        initDisplay()
        layout_login.visibility = View.GONE
        layout_landing.visibility = View.VISIBLE
        layout_loading.visibility  = View.GONE
    }

    override fun showProgress(show: Boolean) {
        layout_loading.visibility = if (show) View.VISIBLE else View.GONE
        layout_login.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showError(error: String) {
        error_login_message.visibility = View.VISIBLE
        error_login_message.text = error
    }

    override fun onLogin(result: LoginResponse) {
        if (result.Errors.isNotEmpty()){
            var message = ""
            for (i in result.Errors){
                message += i.Message
            }
            error_login_message.visibility = View.VISIBLE
            error_login_message.text = message
            return
        }
        if (SerializableSave(context,SerializableSave.userDataFileSessionName).save(result.Data.StudentLogin)){
            startActivity(Intent(context,MainMenu::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun onBackPressed() {
        if (isLanding){
            super.onBackPressed()
        }
        closeLoginForm()
    }

    private fun injectDependency(){
        val listcomponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        listcomponent.inject(this)
    }
}
