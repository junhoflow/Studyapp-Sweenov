package com.example.sweenov.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.sweenov.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

   // private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)


       //val textView: TextView = root.findViewById(R.id.text_dashboard)
        //dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        //val adapter = ProfileAdapter(loadData())

        val profileList1 = arrayListOf (
                Profiles( "객체지향프로그래밍", "while문 연습과제", "12시"),
                Profiles( "리액트", "while문 연습과제", "21시"),
                Profiles( "자바", "while문 연습과제", "1시"),
                Profiles( "공업 수학", "3장 연습과제", "2시"),
                Profiles( "객체지향프로그래밍", "class 연습과제", "2시"),
        Profiles( "일반 수학", "4장 연습과제", "19시"),
        Profiles( "객체지향프로그래밍", "for문 연습과제", "10시"),
        Profiles( "일반 수학", "11장 연습과제", "11시"),
                Profiles( "객체지향프로그래밍", "for문 연습과제", "10시"),
                Profiles( "일반 수학", "2장 연습과제", "11시"),
                Profiles( "객체지향프로그래밍", "switch문 연습과제", "23시"),
                Profiles( "일반 수학", "3장 연습과제", "17시")

        )
        val profileList2 = arrayListOf (
                Profiles( "객체지향프로그래밍", "for문 연습과제", "10시"),
                Profiles( "일반 수학", "2장 연습과제", "11시"),
                Profiles( "객체지향프로그래밍", "while문 연습과제", "12시"),
                Profiles( "리액트", "while문 연습과제", "21시"),
                Profiles( "자바스크립트", "while문 연습과제", "1시"),
                Profiles( "공업 수학", "13장 연습과제", "2시"),
                Profiles( "리액트네이티브", "class 연습과제", "2시"),
                Profiles( "일반 수학", "20장 연습과제", "19시"),
                Profiles( "객체지향프로그래밍", "for문 연습과제", "10시"),
                Profiles( "일반 수학", "26장 연습과제", "11시"),
                Profiles( "자료구조론", "중간고사", "10시"),
                Profiles( "일반 수학", "16장 연습과제", "11시"),
                Profiles( "OS", "window 연습과제", "23시"),
                Profiles( "일반 수학", "13장 연습과제", "17시")
        )

        val profileList3 = arrayListOf (
                Profiles( "객체지향프로그래밍", "if문 연습과제", "3시"),
                Profiles( "일반 수학", "7장 연습과제", "21시")
        )

        val profileList4 = arrayListOf (
                Profiles( "객체지향프로그래밍", "goto문 연습과제", "15시"),
                Profiles( "일반 수학", "1장 연습과제", "18시")
        )

        val profileList5 = arrayListOf (
                Profiles( "객체지향프로그래밍", "switch문 연습과제", "23시"),
                Profiles( "일반 수학", "3장 연습과제", "17시")
        )

        val profileList6 = arrayListOf (
                Profiles( "객체지향프로그래밍", "class 연습과제", "2시"),
                Profiles( "일반 수학", "4장 연습과제", "19시")
        )
        val profileList7 = arrayListOf (
                Profiles( "객체지향프로그래밍", "structure 연습과제", "1시"),
                Profiles( "일반 수학", "5장 연습과제", "12시")
        )
        //root.rv_profile.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        root.rv_profile.setHasFixedSize(true)

        val adapter = ProfileAdapter(profileList2)
        root.rv_profile.adapter = adapter
        root.rv_profile.layoutManager= LinearLayoutManager(context)

        val btn1 = root.sun
        btn1.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.sun.text.toString()
            val adapter = ProfileAdapter(profileList1)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })
        val btn2 = root.mon
        btn2.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.mon.text.toString()
            val adapter = ProfileAdapter(profileList2)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })
        val btn3 = root.tue
        btn3.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.tue.text.toString()
            val adapter = ProfileAdapter(profileList3)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })
        val btn4 = root.wed
        btn4.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.wed.text.toString()
            val adapter = ProfileAdapter(profileList4)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })
        val btn5 = root.thu
        btn5.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.thu.text.toString()
            val adapter = ProfileAdapter(profileList5)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })

        val btn6 = root.fri
        btn6.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.fri.text.toString()
            val adapter = ProfileAdapter(profileList6)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })

        val btn7 = root.sat
        btn7.setOnClickListener(View.OnClickListener {
            root.TodayINFO.text = root.sat.text.toString()
            val adapter = ProfileAdapter(profileList7)
            root.rv_profile.adapter = adapter
            root.rv_profile.layoutManager= LinearLayoutManager(context)

        })









        return root



    }



     fun loadData2(): List<Profiles> {
        val list = mutableListOf<Profiles>()


        val profiles = Profiles("코틀린", "for문 연습문제.", "currentTime")
        val profiles2 = Profiles("OS", "while문 연습문제", "210101 10:19")
         val profiles3 = Profiles("자료구조론", "기말고사", "210130 10:19")
        list.add(profiles)
        list.add(profiles2)
         list.add(profiles3)
        return list
    }

    fun loadData() : List<Profiles>{
        val list = mutableListOf<Profiles>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_plus.setOnClickListener()
        {
            val action = DashboardFragmentDirections.actionNavigationDashboardToAdditionFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

            val profiles = Profiles("객체지향프로그래밍", "for문 연습문제.", "currentTime")
            val profiles2 = Profiles("자바", "while문 연습문제", "210101 10:19")
            list.add(profiles)
            list.add(profiles2)



        return list
    }
}