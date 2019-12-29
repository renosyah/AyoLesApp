package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.category.CategoryModel
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.model.course.VerticalCourseModel
import kotlinx.android.synthetic.main.adapter_vertical_course.view.*

class AdapterVerticalCourse : RecyclerView.Adapter<AdapterVerticalCourse.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<VerticalCourseModel>
    lateinit var onClick : (CourseModel,Int) -> Unit

    constructor(context: Context, list: ArrayList<VerticalCourseModel>, onClick: (CourseModel,Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_vertical_course,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.title.text = "${item.Title}"
        val adapter = AdapterHorizontalCourse(context,item.Courses) { courseModel, i ->
            onClick.invoke(item.Courses.get(i),position)
        }
        holder.list_course.adapter = adapter
        holder.list_course.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapter.notifyDataSetChanged()
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var title : TextView
        lateinit var list_course : RecyclerView

        constructor(itemView: View) : super(itemView) {
            this.title = itemView.findViewById(R.id.title_category_course_textview)
            this.list_course = itemView.findViewById(R.id.horizontal_course_recycleview)
        }
    }
}