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
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCourseCard
import kotlinx.android.synthetic.main.fragment_class.*
import javax.inject.Inject


class FragmentClass : Fragment(),FragmentClassContract.View {

    @Inject
    lateinit var presenter: FragmentClassContract.Presenter

    companion object {
        fun newInstance() = FragmentClass()
    }

    lateinit var ctx: Context


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



        search_class_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // find class
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })


        classes_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination with nested scrollview if scrolled to bottom
            }
        })

        getAllClass()
    }

    fun getAllClass(){
        // set adapter for class
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
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