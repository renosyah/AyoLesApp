package com.syahputrareno975.ayolesapp.ui.activity.register

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.student.RegisterRequest
import com.syahputrareno975.ayolesapp.model.student.RegisterResponse
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivity
import com.syahputrareno975.ayolesapp.ui.activity.main_menu.MainMenu
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(),RegisterActivityContract.View {

    @Inject
    lateinit var presenter : RegisterActivityContract.Presenter

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initWidget()
    }

    @SuppressLint("SetTextI18n")
    fun initWidget(){

        this.context = this@RegisterActivity

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        initDisplay()

        register_button.setOnClickListener {
            if (register_name_edittext.text.toString().isEmpty() || register_email_edittext.text.toString().isEmpty() || register_password_edittext.text.toString().isEmpty()){
                error_message_textview.visibility = View.VISIBLE
                error_message_textview.text = getString(R.string.register_please_fill_form)
                return@setOnClickListener
            }
            presenter.register(
                RegisterRequest(
                    register_name_edittext.text.toString(),
                    register_email_edittext.text.toString(),
                    register_password_edittext.text.toString()
                )
            )
        }

        back_button_imageview.setOnClickListener {
            onBackPressed()
        }

        layout_register.visibility = View.VISIBLE
        layout_loading.visibility = View.GONE
        error_message_textview.visibility = View.GONE
    }

    fun initDisplay(){
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params = main_register_layout.layoutParams
        params.height = size.y
    }

    override fun showProgress(show: Boolean) {
        layout_loading.visibility = if (show) View.VISIBLE else View.GONE
        layout_register.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showError(error: String) {
        error_message_textview.visibility = View.VISIBLE
        error_message_textview.text = error
    }

    override fun onRegister(result : RegisterResponse) {
        if (result.Errors.isNotEmpty()){
            var message = ""
            for (i in result.Errors){
                message += i.Message
            }
            error_message_textview.visibility = View.VISIBLE
            error_message_textview.text = message
            return
        }
        if (SerializableSave(context, SerializableSave.userDataFileSessionName).save(result.Data.StudentRegister)){
            startActivity(Intent(context, MainMenu::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(context,LoginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun injectDependency(){
        val listcomponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        listcomponent.inject(this)
    }
}
