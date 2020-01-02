package com.syahputrareno975.ayolesapp.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.syahputrareno975.ayolesapp.R
import com.syahputrareno975.ayolesapp.model.courseExam.CourseExamModel
import com.syahputrareno975.ayolesapp.model.courseExamAnswer.CourseExamAnswerModel
import com.syahputrareno975.ayolesapp.service.RetrofitService
import kotlinx.android.synthetic.main.adapter_exam.view.*

class AdapterExam : RecyclerView.Adapter<AdapterExam.Holder> {

    lateinit var context: Context
    lateinit var list : ArrayList<CourseExamModel>
    lateinit var onClick: (CourseExamModel,Int) -> Unit
    lateinit var onClickAnswer : (CourseExamAnswerModel,Int,Int) -> Unit

    constructor(context: Context, list: ArrayList<CourseExamModel>,onClick: (CourseExamModel,Int) -> Unit, onClickAnswer: (CourseExamAnswerModel,Int,Int) -> Unit) : super() {
        this.context = context
        this.list = list
        this.onClick = onClick
        this.onClickAnswer = onClickAnswer
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder((context as Activity).layoutInflater.inflate(R.layout.adapter_exam,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.submit_button.visibility = if (item.IsSubmited) View.GONE else View.VISIBLE
        holder.title_exam.text = "Number ${(position + 1)}"
        holder.exam_text.text = item.Text
        Picasso.get()
                .load("${RetrofitService.baseURL}${item.ImageURL}")
                .into(holder.exam_image)

        when (item.TypeExam){
            CourseExamModel.TYPE_TEXT -> {
                holder.layout_image.visibility = View.GONE
                holder.layout_text.visibility = View.VISIBLE
            }
            CourseExamModel.TYPE_IMAGE -> {
                holder.layout_image.visibility = View.VISIBLE
                holder.layout_text.visibility = View.GONE
            }
            CourseExamModel.TYPE_TEXT_AND_IMAGE -> {
                holder.layout_image.visibility = View.VISIBLE
                holder.layout_text.visibility = View.VISIBLE
            }
        }

        holder.submit_button.setOnClickListener {
            onClick.invoke(item,position)
        }

        holder.answer_recycleview.adapter = AdapterExamAnswer(context,item.Answers,position,onClickAnswer)
        holder.answer_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

    }

    class Holder : RecyclerView.ViewHolder {
        lateinit var title_exam : TextView
        lateinit var exam_text : TextView
        lateinit var exam_image : ImageView
        lateinit var layout_text : LinearLayout
        lateinit var layout_image : LinearLayout
        lateinit var answer_recycleview : RecyclerView
        lateinit var submit_button : Button

        constructor(itemView: View) : super(itemView) {
            this.title_exam = itemView.findViewById(R.id.title_exam)
            this.exam_text = itemView.findViewById(R.id.exam_text_textview)
            this.exam_image =  itemView.findViewById(R.id.exam_image_imageview)
            this.layout_text =  itemView.findViewById(R.id.layout_exam_text)
            this.layout_image =  itemView.findViewById(R.id.layout_exam_image)
            this.answer_recycleview =  itemView.findViewById(R.id.exam_answer_recycleview)
            this.submit_button = itemView.findViewById(R.id.submit_button)
        }
    }
}