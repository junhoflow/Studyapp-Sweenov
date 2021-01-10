package com.example.sweenov.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sweenov.R
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.FieldPosition


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
        itemView.tv_name.text = "${profiles.name}"
        itemView.tv_job.text = profiles.job
        itemView.age.text = profiles.age
    }
}