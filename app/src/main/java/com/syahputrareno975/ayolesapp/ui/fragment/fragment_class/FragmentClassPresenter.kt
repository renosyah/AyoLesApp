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

    override fun getAllCategory(r: AllCategoryRequest) {
        //view.showProgress(true)
        val subscribe = api.allCategory(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result : AllCategoryResponse? ->
                //view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllCategory(result)
                }
            },{t : Throwable ->
                //view.showProgress(false)
                view.showError(t.message!!)
            })

        subscriptions.add(subscribe)
    }


    override fun getAllClass(r: AllClassRoomRequest) {
        view.showProgress(true)
        val subscribe = api.allClassRoom(Query(r.toSchema()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result :AllClassRoomResponse? ->
                view.showProgress(false)
                if (result != null){
                    if (result.Errors.isNotEmpty()){
                        var message = ""
                        for (i in result.Errors){
                            message += i.Message
                        }
                        view.showError(message)
                    }
                    view.onGetAllClass(result)
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

    override fun attach(view: FragmentClassContract.View) {
        this.view = view
    }

}