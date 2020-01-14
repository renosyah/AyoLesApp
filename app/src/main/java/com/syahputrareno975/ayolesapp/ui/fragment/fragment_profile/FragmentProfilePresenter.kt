package com.syahputrareno975.ayolesapp.ui.fragment.fragment_profile

import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AllClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AllClassRoomCertificateResponse
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

    override fun getAllClassRoomCertificate(r: AllClassRoomCertificateRequest,enableLoading : Boolean) {
        if (enableLoading){
            view.showProgressOnGetAllClassRoomCertificate(true)
        }
        val subscribe = api.allClassRoomCertificate(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AllClassRoomCertificateResponse? ->
                    if (enableLoading) {
                        view.showProgressOnGetAllClassRoomCertificate(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnGetAllClassRoomCertificate(message)
                        }
                        view.onGetAllClassRoomCertificate(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnGetAllClassRoomCertificate(false)
                    }
                    view.showErrorOnGetAllClassRoomCertificate(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun getAllClass(r: AllClassRoomRequest,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllClass(true)
        }
        val subscribe = api.allClassRoom(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllClassRoomResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllClass(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllClass(message)
                    }
                    view.onGetAllClass(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllClass(false)
                }
                view.showErrorOnGetAllClass(t.message!!)
            })

        subscriptions.add(subscribe)
    }
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

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FragmentProfileContract.View) {
        this.view = view
    }

}