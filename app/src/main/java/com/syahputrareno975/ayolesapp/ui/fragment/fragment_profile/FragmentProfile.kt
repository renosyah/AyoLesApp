package com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerFragmentComponent
import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClassContract
import javax.inject.Inject

class FragmentProfile : Fragment(),FragmentProfileContract.View {

    @Inject
    lateinit var presenter: FragmentProfileContract.Presenter

    companion object {
        fun newInstance() = FragmentProfile()
    }

    lateinit var ctx: Context

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