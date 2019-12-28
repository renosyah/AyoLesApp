package com.syahputrareno975.ayolesapp.ui.activity.login

import com.syahputrareno975.ayolesapp.model.login.LoginRequest
import com.syahputrareno975.ayolesapp.model.login.LoginResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginActivityPresenter : LoginActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: LoginActivityContract.View

    override fun login(login: LoginRequest) {
        view.showProgress(true)
        val subscribe = api.login(Query(login.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : LoginResponse? ->
                view.showProgress(false)
                if (result != null)
                    view.onLogin(result)

            },{ t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }


    override fun subscribe(){}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: LoginActivityContract.View) {
        this.view = view
    }
}