package com.syahputrareno975.ayolesapp.ui.activity.material_detail

import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
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

    override fun getAllCourseMaterial(r: AllCourseMaterialRequest) {
        view.showProgress(true)
        val subscribe = api.allCourseMaterial(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseMaterialResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllCourseMaterial(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun addCourseMaterialProgress(r: AddClassRoomProgressRequest) {
        view.showProgress(true)
        val subscribe = api.addClassRoomProgress(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AddClassRoomProgressResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onAddCourseMaterialProgress(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getAllCourseMaterialDetail(r: AllCourseMaterialDetailRequest) {
        view.showProgress(true)
        val subscribe = api.allCourseMaterialDetail(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseMaterialDetailResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllCourseMaterialDetail(result)
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

    override fun attach(view: MaterialDetailClassRoomActivityContract.View) {
       this.view = view
    }

}