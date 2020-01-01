package com.syahputrareno975.ayolesapp.ui.fragment.fragment_class

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerFragmentComponent
import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomRequest
import com.syahputrareno975.ayolesapp.model.classRoom.AllClassRoomResponse
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.model.course.AllCourseRequest
import com.syahputrareno975.ayolesapp.model.course.AllCourseResponse
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.model.student.StudentModel
import com.syahputrareno975.ayolesapp.ui.activity.material_classroom.MaterialClassRoomActivity
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCategory
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterClassRoom
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCourseCard
import com.syahputrareno975.ayolesapp.ui.fragment.fragment_home.FragmentHome
import com.syahputrareno975.ayolesapp.util.SerializableSave
import kotlinx.android.synthetic.main.fragment_class.*
import javax.inject.Inject


class FragmentClass : Fragment(),FragmentClassContract.View {

    @Inject
    lateinit var presenter: FragmentClassContract.Presenter

    companion object {
        fun newInstance() = FragmentClass()
        private val limit_load_category : Int = 5
        private val limit_load_class : Int = 5
    }

    lateinit var ctx: Context

    lateinit var studentSession : StudentModel

    lateinit var adapterCategory : AdapterCategory
    val categoryList : ArrayList<CategoryModel> = ArrayList()

    val reqAllCategory = AllCategoryRequest()

    lateinit var adapterClassRoom : AdapterClassRoom
    val listClassRoom : ArrayList<ClassRoomModel> = ArrayList()

    val reqAllClass = AllClassRoomRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget(view)
    }

    fun initWidget(v: View) {
        ctx = activity as Context

        presenter.attach(this)
        presenter.subscribe()

        reqAllClass.Limit = limit_load_class
        reqAllCategory.Limit = limit_load_category

        if (SerializableSave(ctx,SerializableSave.userDataFileSessionName).load() != null){
            studentSession = SerializableSave(ctx,SerializableSave.userDataFileSessionName).load() as StudentModel
        }

        search_class_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                clearTagedCategory()
                listClassRoom.clear()
                reqAllClass.Offset = 0
                reqAllClass.SearchValue = search_class_edittext.text.toString()
                presenter.getAllClass(reqAllClass)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })


        classes_nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) {
                // pagination with nested scrollview if scrolled to bottom
                reqAllClass.Offset += limit_load_class
                presenter.getAllClass(reqAllClass)
            }
        })

        getAllClass()
        getAllCategory()
    }

    fun getAllClass(){
        adapterClassRoom = AdapterClassRoom(ctx,listClassRoom) { classRoomModel, i ->
            val intent = Intent(ctx,MaterialClassRoomActivity::class.java)
            intent.putExtra("data",classRoomModel)
            startActivity(intent)
        }
        classes_recycleview.adapter = adapterClassRoom
        classes_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)

        reqAllClass.StudentId = studentSession.Id
        presenter.getAllClass(reqAllClass)
    }

    fun getAllCategory(){
        adapterCategory = AdapterCategory(ctx,categoryList) { categoryModel, i ->
            listClassRoom.clear()
            reqAllClass.Offset = 0
            reqAllClass.SearchBy = "course.category_id::STRING"
            reqAllClass.SearchValue = categoryModel.Id
            presenter.getAllClass(reqAllClass)
        }
        category_classes_recycleview.adapter = adapterCategory
        category_classes_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false)
        category_classes_recycleview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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

    fun checkNoResultFound(forceShow : Boolean){
        not_found.visibility = if (listClassRoom.isEmpty() || forceShow) View.VISIBLE else View.GONE
        classes_recycleview.visibility = if (listClassRoom.isEmpty() || forceShow) View.GONE else View.VISIBLE
    }
    fun clearTagedCategory(){
        for (i in categoryList){
            i.IsClick = false
        }
        adapterCategory.notifyDataSetChanged()
    }
    override fun showProgress(show: Boolean) {
        not_found.visibility = View.GONE
    }

    override fun showError(error: String) {
        checkNoResultFound(true)
    }

    override fun onGetAllClass(s: AllClassRoomResponse) {
        listClassRoom.addAll(s.Data.ClassRoomList)
        adapterClassRoom.notifyDataSetChanged()
        checkNoResultFound(false)
    }

    override fun onGetAllCategory(s: AllCategoryResponse) {
        categoryList.addAll(s.Data.CategoryList)
        adapterCategory.notifyDataSetChanged()
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