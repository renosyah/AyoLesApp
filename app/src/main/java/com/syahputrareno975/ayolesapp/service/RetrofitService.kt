package com.syahputrareno975.ayolesapp.service

import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.OneCategoryResponse
import com.syahputrareno975.ayolesapp.model.classRoom.*
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AddClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AllClassRoomCertificateRequest
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.AllClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomCertificate.OneClassRoomCertificateResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.AddClassRoomExamProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.AllClassRoomExamProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamProgress.DeleteClassRoomExamProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomExamResult.AllClassRoomExamResultResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AddClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.classRoomProgress.AllClassRoomProgressResponse
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.courseDetail.AllCourseDetailResponse
import com.syahputrareno975.ayolesapp.model.courseExam.AllCourseExamResponse
import com.syahputrareno975.ayolesapp.model.courseExamAnswer.AllCourseExamAnswerResponse
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

    @POST("graphql")
    fun allCourseExam(@Body query: Query) : Observable<AllCourseExamResponse>

    @POST("graphql")
    fun allCourseExamAnswer(@Body query: Query) : Observable<AllCourseExamAnswerResponse>

    @POST("graphql")
    fun addClassRoomExamProgress(@Body query: Query) : Observable<AddClassRoomExamProgressResponse>

    @POST("graphql")
    fun allClassRoomExamProgress(@Body query: Query) : Observable<AllClassRoomExamProgressResponse>

    @POST("graphql")
    fun deleteClassRoomExamProgress(@Body query: Query) : Observable<DeleteClassRoomExamProgressResponse>

    @POST("graphql")
    fun allClassRoomExamResult(@Body query: Query) : Observable<AllClassRoomExamResultResponse>

    @POST("graphql")
    fun addClassRoomCertificate(@Body query: Query) : Observable<AddClassRoomCertificateResponse>

    @POST("graphql")
    fun oneClassRoomCertificate(@Body query: Query) : Observable<OneClassRoomCertificateResponse>

    @POST("graphql")
    fun allClassRoomCertificate(@Body query: Query) : Observable<AllClassRoomCertificateResponse>

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