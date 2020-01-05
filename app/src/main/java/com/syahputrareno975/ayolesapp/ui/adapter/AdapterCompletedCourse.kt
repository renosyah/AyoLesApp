package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.classRoom.ClassRoomModel
import com.syahputrareno975.ayolesapp.service.RetrofitService
import kotlinx.android.synthetic.main.activity_material_classroom.*

class AdapterCompletedCourse : RecyclerView.Adapter<AdapterCompletedCourse.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<ClassRoomModel>
    lateinit var onClick : (ClassRoomModel, Int) -> Unit

    constructor(context: Context, list: ArrayList<ClassRoomModel>, onClick: (ClassRoomModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_completed_course,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        Picasso.get()
                .load("${RetrofitService.baseURL}${item.Course.ImageUrl}")
                .into(holder.image)

        holder.title.text = item.Course.CourseName
        holder.subtitle.text = "${context.getString(R.string.by)} ${item.Course.Teacher.Name}"
        if (item.Course.CourseDetails.isNotEmpty()){
            holder.subtitle.text = item.Course.CourseDetails.get(0).OverviewText
        }
        holder.button_completed_classroom.setBackgroundColor(
                if (item.isCompleted)
                    ContextCompat.getColor(context,R.color.colorPrimaryLight)
                else
                    ContextCompat.getColor(context,R.color.textColorLightGray)
        )
        holder.button_completed_classroom.setOnClickListener {
            onClick.invoke(item,position)
        }
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var image : ImageView
        lateinit var title : TextView
        lateinit var subtitle : TextView
        lateinit var button_completed_classroom : Button

        constructor(itemView: View) : super(itemView) {
            this.image = itemView.findViewById(R.id.image_completed_classroom)
            this.title =  itemView.findViewById(R.id.title_completed_classroom)
            this.subtitle =  itemView.findViewById(R.id.subtitle_completed_classroom)
            this.button_completed_classroom  = itemView.findViewById(R.id.button_completed_classroom)
        }
    }
}