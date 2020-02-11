package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.banner.BannerModel

class AdapterBanner : RecyclerView.Adapter<AdapterBanner.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<BannerModel>
    lateinit var onClick : (BannerModel,Int) -> Unit

    constructor(context: Context, list: ArrayList<BannerModel>, onClick: (BannerModel, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_banner,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        if (item.ImageUrl != "") {
            Picasso.get()
                .load("${item.ImageUrl}")
                .into(holder.image)
        }
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var image : ImageView
        constructor(itemView: View) : super(itemView) {
            this.image = itemView.findViewById(R.id.image_banner)
        }
    }
}