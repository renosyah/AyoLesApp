package com.syahputrareno975.ayolesapp.ui.activity.material_classroom

import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailRequest
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MaterialClassRoomActivityPresenter: MaterialClassRoomActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: MaterialClassRoomActivityContract.View

    override fun getOneClassRoomQualification(r: OneClassRoomQualificationRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetOneClassRoomQualification(true)
        }
        val subscribe = api.oneClassRoomQualification(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : OneClassRoomQualificationResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetOneClassRoomQualification(false)
                }
                if (result != null){
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

    override fun getAllClassRoomProgress(r: AllClassRoomProgressRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllClassRoomProgress(true)
        }
        val subscribe = api.allClassRoomProgress(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllClassRoomProgressResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllClassRoomProgress(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllClassRoomProgress(message)
                    }
                    view.onGetAllClassRoomProgress(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllClassRoomProgress(false)
                }
                view.showErrorOnGetAllClassRoomProgress(t.message!!)
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

    override fun getAllCourseMaterial(r: AllCourseMaterialRequest,enableLoading :Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllCourseMaterial(true)
        }
        val subscribe = api.allCourseMaterial(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseMaterialResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourseMaterial(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCourseMaterial(message)
                    }
                    view.onGetAllCourseMaterial(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourseMaterial(false)
                }
                view.showErrorOnGetAllCourseMaterial(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MaterialClassRoomActivityContract.View) {
        this.view = view
    }
}