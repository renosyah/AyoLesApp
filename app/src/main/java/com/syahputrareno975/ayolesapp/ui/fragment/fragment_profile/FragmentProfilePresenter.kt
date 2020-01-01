package com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile

import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.model.student.OneStudentRequest
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_class.FragmentClassContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentProfilePresenter : FragmentProfileContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: FragmentProfileContract.View

    override fun getAllClass(r: AllClassRoomRequest) {
        view.showProgress(true)
        val subscribe = api.allClassRoom(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllClassRoomResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllClass(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }
    override fun getOneStudent(r: OneStudentRequest) {
        view.showProgress(true)
        val subscribe = api.oneStudent(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : OneStudentResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetOneStudent(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FragmentProfileContract.View) {
        this.view = view
    }

}