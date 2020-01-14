package com.syahputrareno975.ayolesapp.ui.activity.material_detail

import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationRequest
import com.syahputrareno975.ayolesapp.model.classRoomQualification.OneClassRoomQualificationResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialRequest
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailRequest
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MaterialDetailClassRoomActivityPresenter : MaterialDetailClassRoomActivityContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view:  MaterialDetailClassRoomActivityContract.View

    override fun getOneClassRoomQualification(r: OneClassRoomQualificationRequest,enableLoading : Boolean) {
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

    override fun getAllCourseMaterial(r: AllCourseMaterialRequest,enableLoading : Boolean) {
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

    override fun addCourseMaterialProgress(r: AddClassRoomProgressRequest,navCode: Int,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnAddCourseMaterialProgress(true)
        }
        val subscribe = api.addClassRoomProgress(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AddClassRoomProgressResponse? ->
                if (enableLoading) {
                    view.showProgressOnAddCourseMaterialProgress(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnAddCourseMaterialProgress(message)
                    }
                    view.onAddCourseMaterialProgress(result,navCode)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnAddCourseMaterialProgress(false)
                }
                view.showErrorOnAddCourseMaterialProgress(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getAllCourseMaterialDetail(r: AllCourseMaterialDetailRequest,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllCourseMaterialDetail(true)
        }
        val subscribe = api.allCourseMaterialDetail(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseMaterialDetailResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourseMaterialDetail(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCourseMaterialDetail(message)
                    }
                    view.onGetAllCourseMaterialDetail(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourseMaterialDetail(false)
                }
                view.showErrorOnGetAllCourseMaterialDetail(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MaterialDetailClassRoomActivityContract.View) {
       this.view = view
    }

}