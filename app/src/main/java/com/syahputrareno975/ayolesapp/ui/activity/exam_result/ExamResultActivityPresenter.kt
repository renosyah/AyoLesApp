package com.syahputrareno975.ayolesapp.ui.activity.exam_result

import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.AllClassRoomExamProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.AllClassRoomExamProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ExamResultActivityPresenter : ExamResultActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: ExamResultActivityContract.View

    override fun getOneClassRoomQualification(r: OneClassRoomQualificationRequest,enableLoading:Boolean) {
        if (enableLoading) {
            view.showProgressOnGetOneClassRoomQualification(true)
        }
        val subscribe = api.oneClassRoomQualification(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result :OneClassRoomQualificationResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetOneClassRoomQualification(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetOneClassRoomQualification(message)
                    }
                    view.onGetOneClassRoomQualification(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetOneClassRoomQualification(false)
                }
                view.showErrorOnGetOneClassRoomQualification(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getOneClassRoomCertificate(r: OneClassRoomCertificateRequest,enableLoading:Boolean) {
        if (enableLoading) {
            view.showProgressOnGetOneClassRoomCertificate(true)
        }
        val subscribe = api.oneClassRoomCertificate(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result :OneClassRoomCertificateResponse? ->
                    if (enableLoading) {
                        view.showProgressOnGetOneClassRoomCertificate(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnGetOneClassRoomCertificate(message)
                        }
                        view.onGetOneClassRoomCertificate(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnGetOneClassRoomCertificate(false)
                    }
                    view.showErrorOnGetOneClassRoomCertificate(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun getAllClassRoomExamResult(r: AllClassRoomExamResultRequest,enableLoading:Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllClassRoomExamResult(true)
        }
        val subscribe = api.allClassRoomExamResult(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result :AllClassRoomExamResultResponse? ->
                    if (enableLoading) {
                        view.showProgressOnGetAllClassRoomExamResult(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnGetAllClassRoomExamResult(message)
                        }
                        view.onGetAllClassRoomExamResult(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnGetAllClassRoomExamResult(false)
                    }
                    view.showErrorOnGetAllClassRoomExamResult(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ExamResultActivityContract.View) {
        this.view = view
    }

}