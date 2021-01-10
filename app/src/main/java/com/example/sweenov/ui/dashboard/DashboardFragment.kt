package com.example.sweenov.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweenov.MainActivity
import com.example.sweenov.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)


       val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val adapter = ProfileAdapter(loadData())
       root.rv_profile.adapter = adapter
        root.rv_profile.layoutManager= LinearLayoutManager(context)

        return root
    }

    fun loadData() : List<Profiles>{
        val list = mutableListOf<Profiles>()
        for(i in 0..100){
            val profiles = Profiles("i", "내용 ${i}을 입력합니다.", "currentTime")
            list.add(profiles)
        }
        return list
    }
}