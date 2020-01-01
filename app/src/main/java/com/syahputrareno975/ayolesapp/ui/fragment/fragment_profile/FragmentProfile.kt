package com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerFragmentComponent
import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse
import com.syahputrareno975.ayolesapp.model.student.StudentModel
import com.syahputrareno975.ayolesapp.ui.activity.login.LoginActivity
import com.syahputrareno975.ayolesapp.ui.activity.update_profile.UpdateProfileActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterClassRoom
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClassContract
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class FragmentProfile : Fragment(),FragmentProfileContract.View {


    @Inject
    lateinit var presenter: FragmentProfileContract.Presenter

    companion object {
        fun newInstance() = FragmentProfile()
        private val limit_load_class : Int = 5
    }

    lateinit var ctx: Context
    lateinit var studentSession : StudentModel

    //temporary
    lateinit var adapterClassRoomComplete : AdapterClassRoom
    val listClassRoomComplete : ArrayList<ClassRoomModel> = ArrayList()

    val reqAllCompletedClass = AllClassRoomRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget(view)
    }

    fun initWidget(v: View) {

        ctx = activity as Context

        presenter.attach(this)
        presenter.subscribe()

        if (SerializableSave(ctx, SerializableSave.userDataFileSessionName).load() != null){
            studentSession = SerializableSave(ctx, SerializableSave.userDataFileSessionName).load() as StudentModel
        }

        student_name_textview.text = studentSession.Name
        student_email_textview.text = studentSession.Email

        reqAllCompletedClass.Limit = limit_load_class
        reqAllCompletedClass.StudentId = studentSession.Id

        loadmore_textview.setOnClickListener {
            reqAllCompletedClass.Offset += limit_load_class
            presenter.getAllClass(reqAllCompletedClass)
        }

        profile_setting_imageview.setOnClickListener {
            val intent = Intent(ctx,UpdateProfileActivity::class.java)
            intent.putExtra("data",studentSession)
            startActivityForResult(intent,100)
        }

        logout_imageview.setOnClickListener {

            AlertDialog.Builder(ctx)
                .setTitle("Logout")
                .setMessage("Are you sure want logout?")
                .setPositiveButton("Yes") { dialog, which ->

                    if (SerializableSave(ctx, SerializableSave.userDataFileSessionName).delete()){
                        startActivity(Intent(ctx,LoginActivity::class.java))
                        (ctx as Activity).finish()
                    }

                    dialog.dismiss()

                }.setNegativeButton("No"){dialog, which ->

                    dialog.dismiss()

                }.setCancelable(false)
                .create()
                .show()

        }

        getProfile()
    }

    fun getProfile(){
        adapterClassRoomComplete = AdapterClassRoom(ctx,listClassRoomComplete){classRoomModel, i ->

        }
        complete_course_recycleview.adapter = adapterClassRoomComplete
        complete_course_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)

        presenter.getOneStudent(OneStudentRequest(studentSession.Id))
        presenter.getAllClass(reqAllCompletedClass)
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {

    }

    override fun onGetOneStudent(s: OneStudentResponse) {
        student_name_textview.text = s.Data.StudentDetail.Name
        student_email_textview.text = s.Data.StudentDetail.Email
    }

    override fun onGetAllClass(s: AllClassRoomResponse) {
        listClassRoomComplete.addAll(s.Data.ClassRoomList)
        layout_completed_course.visibility = if (listClassRoomComplete.isEmpty()) View.GONE else View.VISIBLE
        adapterClassRoomComplete.notifyDataSetChanged()
        loadmore_textview.visibility = if (s.Data.ClassRoomList.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule(this))
            .build()

        listComponent.inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK){
            getProfile()
        }
    }
}