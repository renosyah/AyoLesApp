package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syahputrareno975.ayolesapp.R

class AdapterSimpleText : RecyclerView.Adapter<AdapterSimpleText.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<String>
    lateinit var onClick : (String,Int) -> Unit

    constructor(context: Context,list: ArrayList<String>, onClick: (String, Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_simple_text,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)
        holder.text.text = item
        holder.text.setOnClickListener {
            onClick.invoke(item,position)
        }
    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var text : TextView

        constructor(itemView: View) : super(itemView) {
            this.text = itemView.findViewById(R.id.text)
        }
    }
}