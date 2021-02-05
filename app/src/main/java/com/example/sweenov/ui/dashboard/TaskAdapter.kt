package com.example.sweenov.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweenov.R
import kotlinx.android.synthetic.main.list_item.view.*


//이곳은 과제 목록 창에 있는 리사이클러뷰(리스트뷰와 비슷한 기능, 리스트를 보여줌)를 위한 곳입니다.

class TaskAdapter(val list:ArrayList<Tasks>): RecyclerView.Adapter<Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return Holder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val profiles = list[position]
        holder.setItem(profiles)
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setItem(profiles: Tasks){
        itemView.subname.text = profiles.subjectName
        itemView.assname.text = profiles.assignmentName
        itemView.deadline.text = profiles.deadLine

    }
}