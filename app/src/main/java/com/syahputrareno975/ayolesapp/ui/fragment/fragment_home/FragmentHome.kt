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
import android.view.inputmethod.EditorInfo
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
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivity
import com.syahputrareno975.ayolesapp.ui.activity.search_course.SearchCourseActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterBanner
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCategory
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterVerticalCourse
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.not_found
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

        not_found.visibility = View.GONE

        reqAllCategory.Limit = limit_load_category
        reqAllCategoryForCourse.Limit = limit_load_course

        home_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination if user reach scroll to bottom on course
                reqAllCategoryForCourse.Offset += limit_load_course
                presenter.getAllCategoryForCourse(reqAllCategoryForCourse,false)
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
            search_home_edittext.setText("")
        }
        search_home_edittext.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                go_search_textview.performClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        getAllBanner()
        getAllCategory()
        getAllCourses()
    }

    fun getAllCourses(){
        adapterVerticalCourse = AdapterVerticalCourse(ctx,verticalCourses) {courseModel, i ->
            // on student enroll course
            val intent = Intent(ctx, DetailCourseActivity::class.java)
            intent.putExtra("data",courseModel)
            startActivity(intent)

        }
        course_recycleview.adapter = adapterVerticalCourse
        course_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)
        presenter.getAllCategoryForCourse(reqAllCategoryForCourse,true)
    }

    fun populateHorizontalCourses(){

        for (i in 0..(verticalCourses.size-1)){
            val req = AllCourseRequest()
            req.CategoryId = verticalCourses.get(i).Id
            presenter.getAllCourses(req,i,false)
        }

    }

    fun getAllCategory(){
        adapterCategory = AdapterCategory(ctx,categoryList,false) { categoryModel, i ->
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
                    presenter.getAllCategory(reqAllCategory,false)
                }
            }
        })

        presenter.getAllCategory(reqAllCategory,true)
    }

    fun getAllBanner(){
        var bannerPos = 0
        adapterBanner = AdapterBanner(ctx,bannerList) { bannerModel, i ->

        }
        banner_recycleview.adapter = adapterBanner
        banner_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(banner_recycleview)

//        val mainHandler = Handler(Looper.getMainLooper())
//        mainHandler.post(object : Runnable {
//            override fun run() {
//                if (banner_recycleview == null)
//                    return
//
//                bannerPos = if (bannerPos == bannerList.size) 0 else bannerPos
//                banner_recycleview.smoothScrollToPosition(bannerPos)
//                bannerPos++
//                mainHandler.postDelayed(this, 5000)
//            }
//        })

        presenter.getAllBanner(AllBannerRequest(),true)
    }
    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (verticalCourses.isEmpty() || forceShow) View.VISIBLE else View.GONE
        course_recycleview.visibility = if (verticalCourses.isEmpty() || forceShow) View.GONE else View.VISIBLE
        banner_recycleview.visibility = if (bannerList.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }

    override fun showProgressOnGetAllBanner(show: Boolean) {
        loading_data_banner.visibility = if (show)View.VISIBLE else View.GONE
        banner_recycleview.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnGetAllBanner(error: String) {
        loading_data_banner.visibility = View.GONE
        banner_recycleview.visibility = View.GONE
    }

    override fun showProgressOnGetAllCategory(show: Boolean) {
        loading_data_category.visibility = if (show) View.VISIBLE else View.GONE
        category_recycleview.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnGetAllCategory(error: String) {
        loading_data_category.visibility = View.GONE
        category_recycleview.visibility = View.GONE
    }

    override fun showProgressOnGetAllCategoryForCourse(show: Boolean) {
        loading_data_course.visibility = if (show) View.VISIBLE else View.GONE
        course_recycleview.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun showErrorOnGetAllCategoryForCourse(error: String) {
        loading_data_course.visibility = View.GONE
        checkNoResultFound(true)
    }

    override fun showProgressOnGetAllCourses(show: Boolean) {
        // course for each vertical recycle view course
    }

    override fun showErrorOnGetAllCourses(error: String) {
        // course for each vertical recycle view course
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
        checkNoResultFound(false)
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