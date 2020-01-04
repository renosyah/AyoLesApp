package com.syahputrareno975.ayolesapp.ui.activity.certificate_view

import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivityContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CertificateActivityPresenter : CertificateActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: CertificateActivityContract.View

    override fun getOneClassRoomCertificate(r: OneClassRoomCertificateRequest) {
        view.showProgress(true)
        val subscribe = api.oneClassRoomCertificate(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : OneClassRoomCertificateResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetOneClassRoomCertificate(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe(){}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: CertificateActivityContract.View) {
        this.view = view
    }

}