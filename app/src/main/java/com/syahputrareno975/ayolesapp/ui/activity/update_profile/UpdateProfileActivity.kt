package com.syahputrareno975.ayolesapp.ui.activity.update_profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.student.*
import com.syahputrareno975.ayolesapp.ui.activity.main_menu.MainMenu
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_update_profile.*
import javax.inject.Inject

class UpdateProfileActivity : AppCompatActivity(),UpdateProfileActivityContract.View {

    @Inject
    lateinit var presenter: UpdateProfileActivityContract.Presenter

    lateinit var context: Context
    lateinit var studentData : StudentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        initWidget()
    }

    fun initWidget(){

        context = this@UpdateProfileActivity

        studentData = intent.getSerializableExtra("data") as StudentModel

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        update_name_edittext.setText(studentData.Name)
        update_email_edittext.setText(studentData.Email)

        update_button.setOnClickListener {
            updateStudentData()
        }

        back_imageview.setOnClickListener {
            finish()
        }

        initDisplay()
        getOneStudent()

        error_texview.visibility = View.GONE
    }

    fun initDisplay(){
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params = main_update_profile_layout.layoutParams
        params.height = size.y
    }

    fun getOneStudent(){
        presenter.getOneStudent(OneStudentRequest(studentData.Id),true)
    }

    fun updateStudentData(){
        presenter.updateOneStudent(
            UpdateRequest(
                studentData.Id,
                if ( update_name_edittext.text.toString().trim().isNotEmpty())
                    update_name_edittext.text.toString()
                else studentData.Name,
                if (update_email_edittext.text.toString().trim().isNotEmpty())
                    update_email_edittext.text.toString()
                else studentData.Email,
                if (update_password_edittext.text.toString().trim().isNotEmpty())
                    update_password_edittext.text.toString()
                else studentData.Password),
            true
        )
    }


    override fun showProgressOnGetOneStudent(show: Boolean) {
        layout_loading_update_profile.visibility = if (show) View.VISIBLE else View.GONE
        layout_edit_profile.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnGetOneStudent(error: String) {
        layout_loading_update_profile.visibility = View.GONE
        layout_edit_profile.visibility = View.VISIBLE
        error_texview.visibility = View.VISIBLE
        error_texview.text = error
    }

    override fun showProgressOnOneStudentUpdated(show: Boolean) {
        layout_loading_update_profile.visibility = if (show) View.VISIBLE else View.GONE
        layout_edit_profile.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnOneStudentUpdated(error: String) {
        layout_loading_update_profile.visibility = View.GONE
        layout_edit_profile.visibility = View.VISIBLE
        error_texview.visibility = View.VISIBLE
        error_texview.text = error
    }

    override fun onGetOneStudent(s: OneStudentResponse) {
        update_name_edittext.setText(s.Data.StudentDetail.Name)
        update_email_edittext.setText(s.Data.StudentDetail.Email)
    }

    override fun onOneStudentUpdated(s: UpdateResponse) {
        if (SerializableSave(context, SerializableSave.userDataFileSessionName).save(s.Data.StudentUpdate)){
            setResult(Activity.RESULT_OK)
            finish()
        }
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
