package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.courseMaterialDetail.CourseMaterialDetailModel
import com.syahputrareno975.ayolesapp.service.RetrofitService

class AdapterMaterialDetail : RecyclerView.Adapter<AdapterMaterialDetail.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<CourseMaterialDetailModel>
    lateinit var onClick : (CourseMaterialDetailModel,Int) -> Unit

    constructor(context: Context, list: ArrayList<CourseMaterialDetailModel>, onClick: (CourseMaterialDetailModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_material_detail_content,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.layout_content.visibility = if (item.TypeMaterial == 0) View.VISIBLE else View.GONE
        holder.layout_image.visibility = if (item.TypeMaterial == 1) View.VISIBLE else View.GONE

        holder.title.text = item.Title
        holder.content.text = item.Content.replace("\\n", "\n")

        if (item.ImageUrl != "") {
            Picasso.get()
                .load("${item.ImageUrl}")
                .into(holder.image)
        }
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var title : TextView
        lateinit var content : TextView
        lateinit var image : ImageView
        lateinit var layout_content : LinearLayout
        lateinit var layout_image : LinearLayout

        constructor(itemView: View) : super(itemView) {
            this.title = itemView.findViewById(R.id.title_textview)
            this.content = itemView.findViewById(R.id.content_textView)
            this.image = itemView.findViewById(R.id.image_content)
            this.layout_content = itemView.findViewById(R.id.layout_type_text)
            this.layout_image = itemView.findViewById(R.id.layout_type_image)
        }
    }
}