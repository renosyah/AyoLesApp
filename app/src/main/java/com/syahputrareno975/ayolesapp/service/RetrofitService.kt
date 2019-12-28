package com.syahputrareno975.ayolesapp.service

import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.login.LoginResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitService {

    // add more end point to access
    @Headers("Authorization:Bearer ")
    @POST("graphql")
    fun login(@Body query : Query) : Observable<LoginResponse>

    @POST("graphql")
    fun allBanner(@Body query: Query) : Observable<AllBannerResponse>

    @POST("graphql")
    fun allCategory(@Body query: Query) : Observable<AllCategoryResponse>


    companion object {

        val baseURL = "http://192.168.137.1:8000/"

        fun create() : RetrofitService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()
            return  retrofit.create(RetrofitService::class.java)
        }
    }
}