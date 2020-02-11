package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.courseExamAnswer.CourseExamAnswerModel
import com.syahputrareno975.ayolesapp.util.Abc

class AdapterExamAnswerResult: RecyclerView.Adapter<AdapterExamAnswerResult.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<CourseExamAnswerModel>
    lateinit var studentAnswerID : String
    lateinit var validAnswerID : String

    constructor(context: Context, list: ArrayList<CourseExamAnswerModel>, studentAnswerID: String, validAnswerID: String) : super() {
        this.context = context
        this.list = list
        this.studentAnswerID = studentAnswerID
        this.validAnswerID = validAnswerID
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_exam_answer,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.answer_label.text = if (position <= Abc.Abcs.size) Abc.Abcs.get(position) else "-"
        holder.answer_text.text = item.Text

        if (item.ImageURL != "") {
            Picasso.get()
                .load("${item.ImageURL}")
                .into(holder.answer_image)
        }

        holder.layout_answer.setBackgroundColor(ContextCompat.getColor(context,
                if (item.Id == studentAnswerID)
                    if (studentAnswerID == validAnswerID)
                        R.color.colorPrimaryLight
                    else
                        R.color.colorError
                else if (item.Id == validAnswerID)
                    R.color.colorPrimaryLight
                else
                    android.R.color.transparent))


        when (item.TypeAnswer){
            CourseExamAnswerModel.TYPE_TEXT -> {
                holder.layout_answer_image.visibility = View.GONE
                holder.layout_answer_text.visibility = View.VISIBLE
            }
            CourseExamAnswerModel.TYPE_IMAGE -> {
                holder.layout_answer_image.visibility =  View.VISIBLE
                holder.layout_answer_text.visibility = View.GONE
            }
            CourseExamAnswerModel.TYPE_TEXT_AND_IMAGE -> {
                holder.layout_answer_image.visibility = View.VISIBLE
                holder.layout_answer_text.visibility = View.VISIBLE
            }
        }
        holder.layout_answer.visibility = View.VISIBLE
    }

    class Holder: RecyclerView.ViewHolder {
        lateinit var answer_label : TextView
        lateinit var answer_text : TextView
        lateinit var answer_image : ImageView
        lateinit var layout_answer_text : LinearLayout
        lateinit var layout_answer_image : LinearLayout
        lateinit var layout_answer : LinearLayout

        constructor(itemView: View) : super(itemView) {
            this.answer_label = itemView.findViewById(R.id.label_answer_textview)
            this.answer_text = itemView.findViewById(R.id.answer_text_textview)
            this.answer_image = itemView.findViewById(R.id.answer_image_imageview)
            this.layout_answer_text = itemView.findViewById(R.id.layout_answer_text)
            this.layout_answer_image = itemView.findViewById(R.id.layout_answer_image)
            this.layout_answer_text = itemView.findViewById(R.id.layout_answer)
            this.layout_answer = itemView.findViewById(R.id.layout_answer)
        }
    }
}