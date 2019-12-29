package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.course.CourseModel
import com.syahputrareno975.ayolesapp.service.RetrofitService

class AdapterCourseCard : RecyclerView.Adapter<AdapterCourseCard.Holder>{

    lateinit var context: Context
    lateinit var list : ArrayList<CourseModel>
    lateinit var onClick : (CourseModel, Int) -> Unit

    constructor(context: Context, list: ArrayList<CourseModel>, onClick: (CourseModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_course_card,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)
        Picasso.get()
            .load("${RetrofitService.baseURL}${item.ImageUrl}")
            .into(holder.image)

        holder.name.text = "${item.CourseName}"
        holder.enroll.setOnClickListener {
            onClick.invoke(item,position)
        }

        holder.category_label.text = "Category : ${item.Category.Name}"
        holder.teach_by.text = "By ${item.Teacher.Name}"
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var image : ImageView
        lateinit var name : TextView
        lateinit var category_label : TextView
        lateinit var teach_by : TextView
        lateinit var enroll : Button

        constructor(itemView: View) : super(itemView) {
            this.image = itemView.findViewById(R.id.image_course_imageview)
            this.name = itemView.findViewById(R.id.course_name_textview)
            this.enroll = itemView.findViewById(R.id.enroll_button)
            this.category_label = itemView.findViewById(R.id.category_label_textview)
            this.teach_by = itemView.findViewById(R.id.teach_by_textview)
        }
    }
}