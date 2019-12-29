package com.syahputrareno975.ayolesapp.ui.activity.search_course

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerActivityComponent
import com.syahputrareno975.ayolesapp.di.module.ActivityModule
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.ui.activity.detail_course.DetailCourseActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCategory
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCourseCard
import kotlinx.android.synthetic.main.activity_search_course.*
import javax.inject.Inject

class SearchCourseActivity : AppCompatActivity(),SearchCourseActivityContract.View {


    @Inject
    lateinit var presenter: SearchCourseActivityContract.Presenter
    lateinit var context: Context

    companion object {
        private val limit_load : Int = 4
    }
    lateinit var adapterCategory : AdapterCategory
    val categoryList : ArrayList<CategoryModel> = ArrayList()

    val reqAllCategory = AllCategoryRequest()


    lateinit var adapterCourse: AdapterCourseCard
    var listCourses : ArrayList<CourseModel> = ArrayList()

    val reqAllCourse = AllCourseRequest()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_course)
        initWidget()
    }

    fun initWidget(){
        this.context = this@SearchCourseActivity

        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        reqAllCategory.Limit = limit_load
        reqAllCourse.Limit = limit_load

        if (intent.hasExtra("category_id")){
            reqAllCourse.CategoryId = intent.getStringExtra("category_id")!!
        }

        if (intent.hasExtra("query")){
            val query = intent.getStringExtra("query")!!
            reqAllCourse.SearchValue = query
            search_course_edittext.setText(query)
        }

        search_course_edittext.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                listCourses.clear()
                reqAllCourse.Offset = 0
                reqAllCourse.SearchValue = search_course_edittext.text.toString()
                presenter.getAllCourses(reqAllCourse)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        search_course_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                    // pagination if user reach scroll to bottom on course
                    reqAllCourse.Offset += limit_load
                    presenter.getAllCourses(reqAllCourse)
                }
            })

        getCourse()
        getCategory()
    }

    fun getCourse(){
        adapterCourse = AdapterCourseCard(context,listCourses) { courseModel, i ->
            val intent = Intent(context, DetailCourseActivity::class.java)
            intent.putExtra("data",courseModel)
            startActivity(intent)
        }
        course_recycleview.adapter = adapterCourse
        course_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        presenter.getAllCourses(reqAllCourse)

    }

    fun getCategory(){
        categoryList.add(CategoryModel("","All",""))
        adapterCategory = AdapterCategory(context,categoryList){categoryModel, i ->
            listCourses.clear()
            reqAllCourse.Offset = 0
            reqAllCourse.CategoryId = categoryModel.Id
            presenter.getAllCourses(reqAllCourse)
        }
        category_recycleview.adapter = adapterCategory
        category_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        presenter.getAllCategory(reqAllCategory)
    }


    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {

    }

    override fun onGetAllCourses(s: AllCourseResponse) {
        listCourses.addAll(s.Data.CourseList)
        adapterCourse.notifyDataSetChanged()
    }

    override fun onGetAllCategory(s: AllCategoryResponse) {
        categoryList.addAll(s.Data.CategoryList)
        adapterCategory.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun injectDependency(){
        val listcomponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        listcomponent.inject(this)
    }
}
