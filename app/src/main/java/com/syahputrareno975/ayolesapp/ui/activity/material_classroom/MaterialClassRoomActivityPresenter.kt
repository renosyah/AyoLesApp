package com.syahputrareno975.ayolesapp.ui.activity.material_classroom

import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressRequest
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
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

    override fun getAllClassRoomProgress(r: AllClassRoomProgressRequest) {
        view.showProgress(true)
        val subscribe = api.allClassRoomProgress(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllClassRoomProgressResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllClassRoomProgress(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getAllCourseDetails(r: AllCourseDetailRequest) {
        view.showProgress(true)
        val subscribe = api.allCourseDetails(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseDetailResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllCourseDetails(result)
                }
            },{t : Throwable ->
                view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }

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

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MaterialClassRoomActivityContract.View) {
        this.view = view
    }
}