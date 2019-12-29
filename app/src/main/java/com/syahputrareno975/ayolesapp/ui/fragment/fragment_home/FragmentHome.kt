package com.syahputrareno975.ayolesapp.ui.fragment.fragment_home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerFragmentComponent
import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.model.banner.AllBannerRequest
import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.banner.BannerModel
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.course.VerticalCourseModel
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterBanner
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCategory
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterVerticalCourse
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class FragmentHome : Fragment(),FragmentHomeContract.View {

    @Inject
    lateinit var presenter: FragmentHomeContract.Presenter

    companion object {
        fun newInstance() = FragmentHome()
        private val limit_load_category : Int = 5
        private val limit_load_course : Int = 2
    }

    lateinit var ctx : Context

    lateinit var adapterBanner: AdapterBanner
    val bannerList : ArrayList<BannerModel> = ArrayList()

    lateinit var adapterCategory : AdapterCategory
    val categoryList : ArrayList<CategoryModel> = ArrayList()

    val reqAllCategory = AllCategoryRequest()

    lateinit var adapterVerticalCourse: AdapterVerticalCourse
    var verticalCourses : ArrayList<VerticalCourseModel> = ArrayList()

    val reqAllCategoryForCourse = AllCategoryRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget(view)
    }

    fun initWidget(v : View){
        ctx = activity as Context

        presenter.attach(this)
        presenter.subscribe()

        reqAllCategory.Limit = limit_load_category
        reqAllCategoryForCourse.Limit = limit_load_course

        home_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllCategoryForCourse.Offset += limit_load_course
                presenter.getAllCategoryForCourse(reqAllCategoryForCourse)
            }
        })

        go_search_textview.visibility = View.GONE
        search_home_edittext.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                go_search_textview.visibility = if (search_home_edittext.text.toString().isNotEmpty()) View.VISIBLE else View.GONE
            }

        })
        go_search_textview.setOnClickListener {
            val query = search_home_edittext.text.toString()
            val intent = Intent(ctx,SearchCourseActivity::class.java)
            intent.putExtra("query",query)
            startActivity(intent)
        }

        getAllBanner()
        getAllCategory()
        getAllCourses()
    }

    fun getAllCourses(){
        adapterVerticalCourse = AdapterVerticalCourse(ctx,verticalCourses) {courseModel, i ->
            // on student enroll course

        }
        course_recycleview.adapter = adapterVerticalCourse
        course_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)
        presenter.getAllCategoryForCourse(reqAllCategoryForCourse)
    }

    fun populateHorizontalCourses(){

        for (i in 0 until verticalCourses.size){
            val req = AllCourseRequest()
            req.CategoryId = verticalCourses.get(i).Id
            presenter.getAllCourses(req,i)
        }

    }

    fun getAllCategory(){
        categoryList.add(CategoryModel("","All",""))
        adapterCategory = AdapterCategory(ctx,categoryList) { categoryModel, i ->
            val intent = Intent(ctx,SearchCourseActivity::class.java)
            intent.putExtra("category_id",categoryModel.Id)
            startActivity(intent)
        }
        category_recycleview.adapter = adapterCategory
        category_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false)
        category_recycleview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lln = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = lln.childCount
                val totalItemCount = lln.itemCount
                val pastVisiblesItems = lln.findFirstVisibleItemPosition()
                if (dx > 0 && (visibleItemCount + pastVisiblesItems) >= totalItemCount){
                    // pagination if user reach scroll to right on category
                    reqAllCategory.Offset += limit_load_category
                    presenter.getAllCategory(reqAllCategory)
                }
            }
        })

        presenter.getAllCategory(reqAllCategory)
    }

    fun getAllBanner(){
        var bannerPos = 0
        adapterBanner = AdapterBanner(ctx,bannerList) { bannerModel, i ->

        }
        banner_recycleview.adapter = adapterBanner
        banner_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(banner_recycleview)

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (banner_recycleview == null)
                    return

                bannerPos = if (bannerPos == bannerList.size) 0 else bannerPos
                banner_recycleview.smoothScrollToPosition(bannerPos)
                bannerPos++
                mainHandler.postDelayed(this, 5000)
            }
        })

        presenter.getAllBanner(AllBannerRequest())
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        Toast.makeText(ctx,error,Toast.LENGTH_SHORT).show()
    }

    override fun onGetAllBanner(s: AllBannerResponse) {
        bannerList.clear()
        bannerList.addAll(s.Data.BannerList)
        adapterBanner.notifyDataSetChanged()
    }

    override fun onGetAllCategory(s: AllCategoryResponse) {
        categoryList.addAll(s.Data.CategoryList)
        adapterCategory.notifyDataSetChanged()
    }

    override fun onGetAllCategoryForCourse(s: AllCategoryResponse) {
        for (i in s.Data.CategoryList){
            verticalCourses.add(VerticalCourseModel(i.Id, i.Name, ArrayList()))
        }
        populateHorizontalCourses()
    }

    override fun onGetAllCourses(s: AllCourseResponse, position: Int) {
        verticalCourses[position].Courses.clear()
        verticalCourses[position].Courses.addAll(s.Data.CourseList)
        adapterVerticalCourse.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule(this))
            .build()

        listComponent.inject(this)
    }
}