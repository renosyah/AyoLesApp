package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.category.CategoryModel

class AdapterCategory : RecyclerView.Adapter<AdapterCategory.Holder>{

    lateinit var context: Context
    lateinit var list : ArrayList<CategoryModel>
    lateinit var onClick : (CategoryModel,Int) -> Unit
    var enableClickFeedback : Boolean = true

    constructor(context: Context, list: ArrayList<CategoryModel>, onClick: (CategoryModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    constructor(context: Context, list: ArrayList<CategoryModel>,enableClickFeedback: Boolean, onClick: (CategoryModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
        this.enableClickFeedback = enableClickFeedback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_category,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.name.text = "${item.Name}"
        holder.name.setOnClickListener {
            for (i in 0 until list.size){
                list[i].IsClick = false
            }
            list[position].IsClick = true
            this@AdapterCategory.notifyDataSetChanged()

            onClick.invoke(item,position)
        }
        holder.name.setTextColor(
            context.resources.getColor(if (item.IsClick && enableClickFeedback) R.color.colorPrimaryLight else android.R.color.darker_gray)
        )
    }


    class Holder : RecyclerView.ViewHolder {
        lateinit var name : TextView

        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById(R.id.category_name_textview)
        }
    }
}