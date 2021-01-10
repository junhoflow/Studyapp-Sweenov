package com.example.sweenov.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweenov.R
import kotlinx.android.synthetic.main.list_item.view.*


class ProfileAdapter(val list:List<Profiles>): RecyclerView.Adapter<Holder>(){
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
    fun setItem(profiles: Profiles){
        itemView.subname.text = profiles.subjectname
        itemView.assname.text = profiles.assignmentname
        itemView.deadline.text = profiles.deadline

    }
}