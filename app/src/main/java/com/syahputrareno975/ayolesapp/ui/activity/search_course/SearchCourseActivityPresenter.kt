package com.syahputrareno975.ayolesapp.ui.activity.search_course

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

class SearchCourseActivityPresenter : SearchCourseActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: SearchCourseActivityContract.View

    override fun getAllCourses(r: AllCourseRequest,enableLoading : Boolean) {
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
                    view.onGetAllCourses(result)
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

    override fun subscribe(){}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: SearchCourseActivityContract.View) {
        this.view = view
    }
}