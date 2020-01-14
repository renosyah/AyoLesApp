package com.syahputrareno975.ayolesapp.ui.activity.detail_course

import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AddClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.OneClassByIdRoomResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseQualification.OneCourseQualificationRequest
import com.syahputrareno975.ayolesapp.model.courseQualification.OneCourseQualificationResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailCourseActivityPresenter : DetailCourseActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: DetailCourseActivityContract.View

    override fun getOneCourseQualification(r: OneCourseQualificationRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetOneCourseQualification(true)
        }
        val subscribe = api.oneCourseQualification(Query(r.toSchema()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result : OneCourseQualificationResponse? ->
                    if (enableLoading) {
                        view.showProgressOnGetOneCourseQualification(false)
                    }
                    if (result != null){
                        if (result.Errors.isNotEmpty()){
                            var message = ""
                            for (i in result.Errors){
                                message += i.Message
                            }
                            view.showErrorOnGetOneCourseQualification(message)
                        }
                        view.onGetOneCourseQualification(result)
                    }
                },{t : Throwable ->
                    if (enableLoading) {
                        view.showProgressOnGetOneCourseQualification(false)
                    }
                    view.showErrorOnGetOneCourseQualification(t.message!!)
                })

        subscriptions.add(subscribe)
    }

    override fun getAllCourseDetails(r: AllCourseDetailRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllCourseDetails(true)
        }
        val subscribe = api.allCourseDetails(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseDetailResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourseDetails(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCourseDetails(message)
                    }
                    view.onGetAllCourseDetails(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourseDetails(false)
                }
                view.showErrorOnGetAllCourseDetails(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getOneClassRoomById(r: OneClassByIdRoomRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetOneClassRoomById(true)
        }
        val subscribe = api.oneClassRoomById(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : OneClassByIdRoomResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetOneClassRoomById(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetOneClassRoomById(message)
                    }
                    view.onGetOneClassRoomById(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetOneClassRoomById(false)
                }
                view.showErrorOnGetOneClassRoomById(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun addClassRoom(r: AddClassRoomRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnAddClassRoom(true)
        }
        val subscribe = api.addClassRoom(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AddClassRoomResponse? ->
                if (enableLoading) {
                    view.showProgressOnAddClassRoom(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnAddClassRoom(message)
                    }
                    view.onAddClassRoom(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnAddClassRoom(false)
                }
                view.showErrorOnAddClassRoom(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe(){}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailCourseActivityContract.View) {
        this.view = view
    }

}