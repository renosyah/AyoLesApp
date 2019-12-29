package com.syahputrareno975.ayolesapp.ui.fragment.fragment_class

import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.disposables.CompositeDisposable

class FragmentClassPresenter : FragmentClassContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: FragmentClassContract.View


    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FragmentClassContract.View) {
        this.view = view
    }

}