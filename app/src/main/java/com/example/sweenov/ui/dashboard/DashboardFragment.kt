package com.example.sweenov.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.sweenov.App
import com.example.sweenov.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

   // private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        root.rv_profile.setHasFixedSize(true)


        getTasksList()


        val btn1 = root.sun
        btn1.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.sun.text.toString()
            //val adapter = ProfileAdapter(profileList1)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })
        val btn2 = root.mon
        btn2.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.mon.text.toString()
            //val adapter = ProfileAdapter(profileList2)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })
        val btn3 = root.tue
        btn3.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.tue.text.toString()
            //val adapter = ProfileAdapter(profileList3)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })
        val btn4 = root.wed
        btn4.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.wed.text.toString()
            //val adapter = ProfileAdapter(profileList4)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })
        val btn5 = root.thu
        btn5.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.thu.text.toString()
            //val adapter = ProfileAdapter(profileList5)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })

        val btn6 = root.fri
        btn6.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.fri.text.toString()
            //val adapter = ProfileAdapter(profileList6)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })

        val btn7 = root.sat
        btn7.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.sat.text.toString()
            //val adapter = ProfileAdapter(profileList7)
            //root.rv_profile.adapter = adapter
            //root.rv_profile.layoutManager= LinearLayoutManager(context)
            getTasksList()
            val adapter = TaskAdapter(App.questList)
            root.rv_profile.adapter = adapter!!
            root.rv_profile.layoutManager= LinearLayoutManager(context)
        })
        // Fragment 클래스에서 사용 시

        val adapter = TaskAdapter(App.questList)
        root.rv_profile.adapter = adapter!!
        root.rv_profile.layoutManager= LinearLayoutManager(context)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        btn_plus.setOnClickListener()
        {
            val action = DashboardFragmentDirections.actionNavigationDashboardToAdditionFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }






    fun getTasksList() {

        //필요한 것 = 과제를 할당 받은 팔로워의 이름
        // val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("홍길동")

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                App.questList.clear()
                // val currentUser = snapshot.getValue(User::class.java)
                //if(currentUser!!.profileImage == ""){
                //     img

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val quest = dataSnapShot.getValue(Tasks::class.java)

                    if (quest!!.deadLine.equals("2021년 2월 3일")) {
                        App.questList.add(quest)
                    }
                }


            }


        })
    }

}