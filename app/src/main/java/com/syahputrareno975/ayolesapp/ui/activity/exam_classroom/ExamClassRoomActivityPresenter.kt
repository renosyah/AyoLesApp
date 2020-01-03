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

    override fun addClassRoomCertificate(r: AddClassRoomCertificateRequest) {
        view.showProgress(true)
        val subscribe = api.addClassRoomCertificate(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AddClassRoomCertificateResponse? ->
                    view.showProgress(false)
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showError(message)
                        }
                        view.onAddClassRoomCertificate(result)
                    }
                },{t : Throwable ->
                    view.showProgress(false)
                    view.showError(t.message!!)
                })

        subscriptions.add(subscribe)
    }
    override fun getAllClassRoomExamProgress(r: AllClassRoomExamProgressRequest) {
        view.showProgress(true)
        val subscribe = api.allClassRoomExamProgress(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AllClassRoomExamProgressResponse? ->
                    view.showProgress(false)
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showError(message)
                        }
                        view.onGetAllClassRoomExamProgress(result)
                    }
                },{t : Throwable ->
                    view.showProgress(false)
                    view.showError(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun addClassRoomExamProgress(r: AddClassRoomExamProgressRequest,posExampSubmited : Int) {
        view.showProgress(true)
        val subscribe = api.addClassRoomExamProgress(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AddClassRoomExamProgressResponse? ->
                    view.showProgress(false)
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showError(message)
                        }
                        view.onAddClassRoomExamProgress(result,posExampSubmited)
                    }
                },{t : Throwable ->
                    view.showProgress(false)
                    view.showError(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun getAllExam(r: AllCourseExamRequest) {
        view.showProgress(true)
        val subscribe = api.allCourseExam(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : AllCourseExamResponse? ->
                    view.showProgress(false)
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showError(message)
                        }
                        view.onGetAllExam(result)
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

    override fun attach(view: ExamClassRoomActivityContract.View) {
        this.view = view
    }
}