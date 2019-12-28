package com.syahputrareno975.ayolesapp.ui.fragment.fragment_home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.di.component.DaggerFragmentComponent
import com.syahputrareno975.ayolesapp.di.module.FragmentModule
import com.syahputrareno975.ayolesapp.model.banner.AllBannerRequest
import com.syahputrareno975.ayolesapp.model.banner.AllBannerResponse
import com.syahputrareno975.ayolesapp.model.banner.BannerModel
import com.syahputrareno975.ayolesapp.model.category.AllCategoryRequest
import com.syahputrareno975.ayolesapp.model.category.AllCategoryResponse
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterBanner
import com.syahputrareno975.ayolesapp.ui.adapter.AdapterCategory
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class FragmentHome : Fragment(),FragmentHomeContract.View {

    @Inject
    lateinit var presenter: FragmentHomeContract.Presenter

    companion object {
        fun newInstance() = FragmentHome()
    }

    lateinit var ctx : Context

    lateinit var adapterBanner: AdapterBanner
    val bannerList : ArrayList<BannerModel> = ArrayList()

    lateinit var adapterCategory : AdapterCategory
    val categoryList : ArrayList<CategoryModel> = ArrayList()

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

        getAllBanner()
        getAllCategory()
    }

    fun getAllCategory(){
        adapterCategory = AdapterCategory(ctx,categoryList) { categoryModel, i ->

        }
        category_recycleview.adapter = adapterCategory
        category_recycleview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false)
        presenter.getAllCategory(AllCategoryRequest())
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
        categoryList.clear()
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