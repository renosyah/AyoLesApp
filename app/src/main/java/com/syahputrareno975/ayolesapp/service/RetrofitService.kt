package com.syahputrareno975.ayolesapp.service

import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.OneCategoryResponse
import com.syahputrareno975.ayolesapp.model.classRoom.*
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseMaterial.AllCourseMaterialResponse
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.AllCourseMaterialDetailResponse
import com.syahputrareno975.ayolesapp.model.student.LoginResponse
import com.syahputrareno975.ayolesapp.model.queryModel.Query
import com.syahputrareno975.ayolesapp.model.student.RegisterResponse
import com.syahputrareno975.ayolesapp.model.student.OneStudentResponse
import com.syahputrareno975.ayolesapp.model.student.UpdateResponse
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
    fun register(@Body query: Query) : Observable<RegisterResponse>

    @POST("graphql")
    fun update(@Body query: Query) : Observable<UpdateResponse>

    @POST("graphql")
    fun allBanner(@Body query: Query) : Observable<AllBannerResponse>

    @POST("graphql")
    fun allCategory(@Body query: Query) : Observable<AllCategoryResponse>

    @POST("graphql")
    fun oneCategory(@Body query: Query) : Observable<OneCategoryResponse>

    @POST("graphql")
    fun allCourses(@Body query: Query) : Observable<AllCourseResponse>

    @POST("graphql")
    fun allCourseDetails(@Body query: Query) : Observable<AllCourseDetailResponse>

    @POST("graphql")
    fun allClassRoom(@Body query: Query) : Observable<AllClassRoomResponse>

    @POST("graphql")
    fun addClassRoom(@Body query: Query) : Observable<AddClassRoomResponse>

    @POST("graphql")
    fun oneClassRoomById(@Body query: Query) : Observable<OneClassByIdRoomResponse>

    @POST("graphql")
    fun oneStudent(@Body query: Query) : Observable<OneStudentResponse>

    @POST("graphql")
    fun allCourseMaterial(@Body query: Query) : Observable<AllCourseMaterialResponse>

    @POST("graphql")
    fun allCourseMaterialDetail(@Body query: Query) : Observable<AllCourseMaterialDetailResponse>

    @POST("graphql")
    fun allClassRoomProgress(@Body query: Query) : Observable<AllClassRoomProgressResponse>

    @POST("graphql")
    fun addClassRoomProgress(@Body query: Query) : Observable<AddClassRoomProgressResponse>

    companion object {

        val baseURL = "http://192.168.9.101:8000/"

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