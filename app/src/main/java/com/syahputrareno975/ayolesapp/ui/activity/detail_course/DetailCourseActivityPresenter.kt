package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.disposables.CompositeDisposable

class DetailCourseActivityPresenter : DetailCourseActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: DetailCourseActivityContract.View

    override fun subscribe(){}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailCourseActivityContract.View) {
        this.view = view
    }

}