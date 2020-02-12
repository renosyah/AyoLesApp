package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.service.RetrofitService

class AdapterHorizontalCourse : RecyclerView.Adapter<AdapterHorizontalCourse.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<CourseModel>
    lateinit var onClick : (CourseModel,Int) -> Unit

    constructor(context: Context, list: ArrayList<CourseModel>, onClick: (CourseModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_horizontal_course,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        if (item.ImageUrl != "") {
            Picasso.get()
                .load("${item.ImageUrl}")
                .resize(200, 150)
                .into(holder.image)
        }

        holder.name.text = "${item.CourseName}"
        holder.enroll.setOnClickListener {
            onClick.invoke(item,position)
        }
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var image : ImageView
        lateinit var name : TextView
        lateinit var enroll : LinearLayout

        constructor(itemView: View) : super(itemView) {
            this.image = itemView.findViewById(R.id.image_course)
            this.name = itemView.findViewById(R.id.course_name)
            this.enroll = itemView.findViewById(R.id.couse_layout_linearlayout)
        }
    }
}