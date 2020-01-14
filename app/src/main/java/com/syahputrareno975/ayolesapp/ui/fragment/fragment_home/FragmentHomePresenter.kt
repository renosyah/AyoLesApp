package com.syahputrareno975.ayolesapp.ui.fragment.fragment_home

import com.syahputrareno975.ayolesapp.model.banner.AllBannerRequest
import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentHomePresenter : FragmentHomeContract.Presenter {


    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: FragmentHomeContract.View

    override fun getAllCategoryForCourse(r: AllCategoryRequest,enableLoading : Boolean) {
        if (enableLoading){
            view.showProgressOnGetAllCategoryForCourse(true)
        }
        val subscribe = api.allCategory(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCategoryResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllCategoryForCourse(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCategoryForCourse(message)
                    }
                    view.onGetAllCategoryForCourse(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllCategoryForCourse(false)
                }
                view.showErrorOnGetAllCategoryForCourse(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getAllCourses(r: AllCourseRequest,position : Int,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllCourses(true)
        }
        val subscribe = api.allCourses(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCourseResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourses(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCourses(message)
                    }
                    view.onGetAllCourses(result,position)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllCourses(false)
                }
                view.showErrorOnGetAllCourses(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getAllCategory(r: AllCategoryRequest,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllCategory(true)
        }
        val subscribe = api.allCategory(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCategoryResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllCategory(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCategory(message)
                    }
                    view.onGetAllCategory(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllCategory(false)
                }
                view.showErrorOnGetAllCategory(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun getAllBanner(r: AllBannerRequest,enableLoading : Boolean) {
        if (enableLoading) {
            view.showProgressOnGetAllBanner(true)
        }
        val subscribe = api.allBanner(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllBannerResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllBanner(false)
                }
                if (result != null) {
                    if (result.Errors.isNotEmpty()) {
                        var message = ""
                        for (i in result.Errors) {
                            message += i.Message + "\n"
                        }
                        view.showErrorOnGetAllBanner(message)
                    }
                    view.onGetAllBanner(result)
                }
            },{ t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllBanner(false)
                }
                view.showErrorOnGetAllBanner(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FragmentHomeContract.View) {
        this.view = view
    }
}