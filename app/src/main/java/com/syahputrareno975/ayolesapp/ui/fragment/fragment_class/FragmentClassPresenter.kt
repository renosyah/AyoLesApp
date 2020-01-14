package com.syahputrareno975.ayolesapp.ui.fragment.fragment_class

import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentClassPresenter : FragmentClassContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api : RetrofitService = RetrofitService.create()
    private lateinit var view: FragmentClassContract.View

    override fun getAllCategory(r: AllCategoryRequest,enableLoading : Boolean) {
        if (enableLoading){
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


    override fun getAllClass(r: AllClassRoomRequest,enableLoading : Boolean) {
        if (enableLoading){
            view.showProgressOnGetAllClass(true)
        }
        val subscribe = api.allClassRoom(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result :AllClassRoomResponse? ->
                if (enableLoading) {
                    view.showProgressOnGetAllClass(false)
                }
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showErrorOnGetAllCategory(message)
                    }
                    view.onGetAllClass(result)
                }
            },{t : Throwable ->
                if (enableLoading) {
                    view.showProgressOnGetAllClass(false)
                }
                view.showErrorOnGetAllClass(t.message!!)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FragmentClassContract.View) {
        this.view = view
    }

}