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
import com.syahputrareno975.ayolesapp.model.courseMaterial.CourseMaterialModel


class AdapterMaterialClassRoom : RecyclerView.Adapter<AdapterMaterialClassRoom.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<CourseMaterialModel>
    lateinit var onClick : (CourseMaterialModel,Int) -> Unit

    constructor(context: Context, list: ArrayList<CourseMaterialModel>, onClick: (CourseMaterialModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_material_classroom,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.title.text = item.Title
        holder.open.setOnClickListener {
            onClick.invoke(item,position)
        }

        Picasso.get()
            .load(if (item.IsCompleted)
                R.drawable.completed_b
            else
                R.drawable.not_completed
            ).into(holder.status_progress)
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var title : TextView
        lateinit var open : Button
        lateinit var status_progress : ImageView

        constructor(itemView: View) : super(itemView) {
            this.title = itemView.findViewById(R.id.title_textview)
            this.open = itemView.findViewById(R.id.open_button)
            this.status_progress = itemView.findViewById(R.id.status_progress)
        }
    }
}