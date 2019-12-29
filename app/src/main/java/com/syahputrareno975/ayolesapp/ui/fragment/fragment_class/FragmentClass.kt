package com.syahputrareno975.ayolesapp.ui.fragment.fragment_class

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerFragmentComponent
import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.model.student.StudentModel
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterClassRoom
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCourseCard
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.fragment_class.*
import javax.inject.Inject


class FragmentClass : Fragment(),FragmentClassContract.View {


    @Inject
    lateinit var presenter: FragmentClassContract.Presenter

    companion object {
        fun newInstance() = FragmentClass()
        private val limit_load_class : Int = 5
    }

    lateinit var ctx: Context

    lateinit var studentSession : StudentModel

    lateinit var adapterClassRoom : AdapterClassRoom
    val listClassRoom : ArrayList<ClassRoomModel> = ArrayList()

    val reqAllClass = AllClassRoomRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget(view)
    }

    fun initWidget(v: View) {
        ctx = activity as Context

        presenter.attach(this)
        presenter.subscribe()

        reqAllClass.Limit = limit_load_class

        if (SerializableSave(ctx,SerializableSave.userDataFileSessionName).load() != null){
            studentSession = SerializableSave(ctx,SerializableSave.userDataFileSessionName).load() as StudentModel
        }

        search_class_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // find class
                listClassRoom.clear()
                reqAllClass.Offset = 0
                reqAllClass.SearchValue = search_class_edittext.text.toString()
                presenter.getAllClass(reqAllClass)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })


        classes_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination with nested scrollview if scrolled to bottom
                reqAllClass.Offset += limit_load_class
                presenter.getAllClass(reqAllClass)
            }
        })

        getAllClass()
    }

    fun getAllClass(){
        adapterClassRoom = AdapterClassRoom(ctx,listClassRoom) { classRoomModel, i ->

        }
        classes_recycleview.adapter = adapterClassRoom
        classes_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)

        reqAllClass.StudentId = studentSession.Id
        presenter.getAllClass(reqAllClass)
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
    }

    override fun onGetAllClass(s: AllClassRoomResponse) {
        listClassRoom.addAll(s.Data.ClassRoomList)
        adapterClassRoom.notifyDataSetChanged()
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
}