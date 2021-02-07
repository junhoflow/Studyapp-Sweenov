package com.example.sweenov.ui.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.sweenov.App
import com.example.sweenov.ForLoading
import com.example.sweenov.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.list_item.view.*


//이곳은 과제 목록 창에 있는 리사이클러뷰(리스트뷰와 비슷한 기능, 리스트를 보여줌)를 위한 곳입니다.
var subName : String = ""
var assName : String = ""
class TaskAdapter(val context: Context, val list:ArrayList<Tasks>): RecyclerView.Adapter<Holder>(){

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
        holder.btnd.setOnClickListener {

            deleteTask(App.name, holder.ForSubName.text.toString(), holder.ForAssName.text.toString())
            Toast.makeText(context, "과제 ${holder.ForAssName.text} 가 제거되었습니다", Toast.LENGTH_LONG).show()
            val intent3 = Intent(context, ForLoading::class.java)
            context.startActivity(intent3)

        }


    }
    fun deleteTask(userName: String, subjectName:String, assignmentName : String){
//필요한 것 = 업애려고하는 과제의 이름과 그 과제를 할당받은 팔로워의 이름
        var stringForData = "$subjectName-$assignmentName"
        FirebaseDatabase.getInstance().getReference().child(userName).child(stringForData).removeValue()
    }
    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        var ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.detach(fragment).attach(fragment).commit()
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var btnd = itemView?.findViewById<Button>(R.id.buttonForDelete)
    //val layoutQuest = itemView?.findViewById<LinearLayout>(R.id.layoutQuest)
    val ForSubName =itemView?.findViewById<TextView>(R.id.subname)
    val ForAssName = itemView?.findViewById<TextView>(R.id.assname)
    fun setItem(Tasks: Tasks){

        itemView.subname.text = Tasks.subjectName
        itemView.assname.text = Tasks.assignmentName
        itemView.deadline.text = Tasks.deadLine

    }


}