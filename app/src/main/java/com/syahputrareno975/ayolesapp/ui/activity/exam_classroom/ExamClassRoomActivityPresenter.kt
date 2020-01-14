package com.syahputrareno975.ayolesapp.ui.activity.exam_classroom

import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.*
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamRequest
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.model.student.LoginResponse
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ExamClassRoomActivityPresenter : ExamClassRoomActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: ExamClassRoomActivityContract.View

    override fun addClassRoomCertificate(r: AddClassRoomCertificateRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnAddClassRoomCertificate(true)
        }
        val subscribe = api.addClassRoomCertificate(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AddClassRoomCertificateResponse? ->
                    if (enableLoading) {
                        view.showProgressOnAddClassRoomCertificate(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnAddClassRoomCertificate(message)
                        }
                        view.onAddClassRoomCertificate(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnAddClassRoomCertificate(false)
                    }
                    view.showErrorOnAddClassRoomCertificate(t.message!!)
                })

        subscriptions.add(subscribe)
    }
    override fun getAllClassRoomExamProgress(r: AllClassRoomExamProgressRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllClassRoomExamProgress(true)
        }
        val subscribe = api.allClassRoomExamProgress(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AllClassRoomExamProgressResponse? ->
                    if (enableLoading) {
                        view.showProgressOnGetAllClassRoomExamProgress(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnGetAllClassRoomExamProgress(message)
                        }
                        view.onGetAllClassRoomExamProgress(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnGetAllClassRoomExamProgress(false)
                    }
                    view.showErrorOnGetAllClassRoomExamProgress(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun addClassRoomExamProgress(r: AddClassRoomExamProgressRequest,posExampSubmited : Int,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnAddClassRoomExamProgress(true)
        }
        val subscribe = api.addClassRoomExamProgress(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AddClassRoomExamProgressResponse? ->
                    if (enableLoading) {
                        view.showProgressOnAddClassRoomExamProgress(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnAddClassRoomExamProgress(message)
                        }
                        view.onAddClassRoomExamProgress(result,posExampSubmited)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnAddClassRoomExamProgress(false)
                    }
                    view.showErrorOnAddClassRoomExamProgress(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun getAllExam(r: AllCourseExamRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllExam(true)
        }
        val subscribe = api.allCourseExam(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AllCourseExamResponse? ->
                    if (enableLoading) {
                        view.showProgressOnGetAllExam(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnGetAllExam(message)
                        }
                        view.onGetAllExam(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnGetAllExam(false)
                    }
                    view.showErrorOnGetAllExam(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ExamClassRoomActivityContract.View) {
        this.view = view
    }
}