package com.syahputrareno975.ayolesapp.ui.activity.exam_result

import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.AllClassRoomExamProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.AllClassRoomExamProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ExamResultActivityPresenter : ExamResultActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: ExamResultActivityContract.View


    override fun getAllClassRoomExamResult(r: AllClassRoomExamResultRequest) {
        view.showProgress(true)
        val subscribe = api.allClassRoomExamResult(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result :AllClassRoomExamResultResponse? ->
                    view.showProgress(false)
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showError(message)
                        }
                        view.onGetAllClassRoomExamResult(result)
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

    override fun attach(view: ExamResultActivityContract.View) {
        this.view = view
    }

}