package com.syahputrareno975.ayolesapp.ui.activity.update_profile

import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse
import com.syahputrareno975.ayolesapp.model.student.UpdateRequest
import com.syahputrareno975.ayolesapp.model.student.UpdateResponse
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpdateProfileActivityPresenter  :UpdateProfileActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: UpdateProfileActivityContract.View

    override fun getOneStudent(r: OneStudentRequest,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnGetOneStudent(true)
        }
        val subscribe = api.oneStudent(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : OneStudentResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetOneStudent(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetOneStudent(message)
                    }
                    view.onGetOneStudent(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetOneStudent(false)
                }
                view.showErrorOnGetOneStudent(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun updateOneStudent(r: UpdateRequest,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnOneStudentUpdated(true)
        }
        val subscribe = api.update(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : UpdateResponse? ->
                if (enableLoading) {
                    view.showProgressOnOneStudentUpdated(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnOneStudentUpdated(message)
                    }
                    view.onOneStudentUpdated(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnOneStudentUpdated(false)
                }
                view.showErrorOnOneStudentUpdated(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: UpdateProfileActivityContract.View) {
        this.view = view
    }

}