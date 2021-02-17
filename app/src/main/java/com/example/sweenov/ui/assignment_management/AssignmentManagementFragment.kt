package com.example.sweenov.ui.assignment_management

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.Navigation
import com.example.sweenov.App
import com.example.sweenov.ForLoading
import com.example.sweenov.MainActivity
import com.example.sweenov.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_a_m.*
import kotlinx.android.synthetic.main.fragment_a_m.view.*
import java.util.*

class AssignmentManagementFragment : Fragment() {
    // 이곳은 과제 목록 창의 동작을 담당하는 프래그먼트 입니다.
    // 이곳에서의 코드가 과제 목록 레이아웃과 상호작용합니다.
    var sunday : String=""
    var monday : String=""
    var tueday : String=""
    var wedday : String=""
    var thuday : String=""
    var friday : String=""
    var satday : String=""

    fun timeGenerator() :String{
        val day = Calendar.getInstance()
        val year = day.get(Calendar.YEAR).toString()
        var month = (day.get(Calendar.MONTH) + 1).toString()
        var date = day.get(Calendar.DATE).toString()
        if (month.toInt() < 10) {
            month = "0$month"
        }
        if (date.toInt() < 10) {
            date = "0$date"
        }

        val today = "$year-$month-$date"

        return today
    }
    fun todayGenerator() :String{

        val dateArray = timeGenerator().split("-").toTypedArray()
        val cal = Calendar.getInstance()
        cal[dateArray[0].toInt(), dateArray[1].toInt() - 1] = dateArray[2].toInt()
        // 요일 확인(일요일:1, 월요일:2, ... ,토요일:7)

        var dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        var dayOfWeekString : String = "ha"
        if(dayOfWeek == 1) dayOfWeekString = "sun"
        else if (dayOfWeek == 2) dayOfWeekString = "mon"
        else if (dayOfWeek == 3) dayOfWeekString = "tue"
        else if (dayOfWeek == 4) dayOfWeekString = "wed"
        else if (dayOfWeek == 5) dayOfWeekString = "thu"
        else if (dayOfWeek == 6) dayOfWeekString = "fri"
        else if (dayOfWeek == 7) dayOfWeekString = "sat"

        return dayOfWeekString
    }

fun datesetting(){
    val dateArray = timeGenerator().split("-").toTypedArray()
    if (todayGenerator() == "sun"){
        sunday = dateArray[2] + " (Today)"
        var hi = dateArray[2].toInt()
        monday = (hi + 1).toString()
        tueday = (hi + 2).toString()
        wedday = (hi + 3).toString()
        thuday = (hi + 4).toString()
        friday = (hi + 5).toString()
        satday = (hi - 1).toString()
    }
    if (todayGenerator() == "mon"){
        monday = dateArray[2]+ " (Today)"
        var hi = dateArray[2].toInt()
        tueday = (hi + 1).toString()
        wedday = (hi + 2).toString()
        thuday = (hi + 3).toString()
        friday = (hi + 4).toString()
        satday = (hi + 5).toString()
        sunday = (hi - 1).toString()
    }
    if (todayGenerator() == "tue"){
        tueday = dateArray[2]+ " (Today)"
        var hi = dateArray[2].toInt()
        wedday = (hi + 1).toString()
        thuday = (hi + 2).toString()
        friday = (hi + 3).toString()
        satday = (hi + 4).toString()
        sunday = (hi + 5).toString()
        monday = (hi - 1).toString()
    }
    if (todayGenerator() == "wed"){
        wedday = dateArray[2]+ " (Today)"
        var hi = dateArray[2].toInt()
        thuday = (hi + 1).toString()
        friday = (hi + 2).toString()
        satday = (hi + 3).toString()
        sunday = (hi + 4).toString()
        monday = (hi + 5).toString()
        tueday = (hi - 1).toString()
    }
    if (todayGenerator() == "thu"){
        thuday = dateArray[2]+ " (Today)"
        var hi = dateArray[2].toInt()
        friday = (hi + 1).toString()
        satday = (hi + 2).toString()
        sunday = (hi + 3).toString()
        monday = (hi + 4).toString()
        tueday = (hi + 5).toString()
        wedday = (hi - 1).toString()
    }
    if (todayGenerator() == "fri"){
        friday = dateArray[2]+ " (Today)"
        var hi = dateArray[2].toInt()
        satday = (hi + 1).toString()
        sunday = (hi + 2).toString()
        monday = (hi + 3).toString()
        tueday = (hi + 4).toString()
        wedday = (hi + 5).toString()
        thuday = (hi - 1).toString()
    }
    if (todayGenerator() == "sat"){
        satday = dateArray[2]+ " (Today)"
        var hi = dateArray[2].toInt()
        sunday = (hi + 1).toString()
        monday = (hi + 2).toString()
        tueday = (hi + 3).toString()
        wedday = (hi + 4).toString()
        thuday = (hi + 5).toString()
        friday = (hi - 1).toString()
    }
}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        datesetting()
        val root = inflater.inflate(R.layout.fragment_a_m, container, false)
        //위 변수 root는 fragment_dashboard 라는 레이아웃과
        //지금 이 프래그먼트(DashboardFragment.kt)의 연결고리 역할을 하는 역할인 것 같습니다
        root.rv_Tasks.setHasFixedSize(true)//리사이클러뷰의 사이즈 설정과 관련된 코드인 것 같다
        App.m1.pause()
        App.m2.pause()


        fun getTasksList( ForDayOfWeek : Int) {
            val dateArray = timeGenerator().split("-").toTypedArray()
            //파이어베이스에서 과제 list를 가져오는 함수
            //현재는 단순하게 자신의 이름 폴더 명에 있는 과제들을 모두 가져오도록 해주어
            //날짜 별로 과제를 가져오지는 못 합니다.
            var ForDayOfWeek2 : String = " "
            var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(App.name)
            var ForDate : String = ""
            if(ForDayOfWeek == 1) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${sunday}"
                val dayForGet = sunday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.sun.text.toString()
                ForDayOfWeek2 = "sun"
            }
            else if(ForDayOfWeek == 2) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${monday}"
                val dayForGet = monday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.mon.text.toString()
                ForDayOfWeek2 = "mon"}
            else if(ForDayOfWeek == 3) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${tueday}"
                val dayForGet = tueday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.tue.text.toString()
                ForDayOfWeek2 = "tue"}
            else if(ForDayOfWeek == 4) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${wedday}"
                val dayForGet = wedday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.wed.text.toString()
                ForDayOfWeek2 = "wed"}
            else if(ForDayOfWeek == 5) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${thuday}"
                val dayForGet = thuday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.thu.text.toString()
                ForDayOfWeek2 = "thu"}
            else if(ForDayOfWeek == 6) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${friday}"
                val dayForGet = friday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.fri.text.toString()
                ForDayOfWeek2 = "fri"}
            else if(ForDayOfWeek == 7) {
                root.date.text = "${dateArray[0]}-${dateArray[1]}-${satday}"
                val dayForGet = satday.split(" ").toTypedArray()
                ForDate = dayForGet[0]
                root.TodayINFO.text = root.sat.text.toString()
                ForDayOfWeek2 = "sat"}

            databaseReference.addValueEventListener(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    App.questList.clear()

                    for (dataSnapShot: DataSnapshot in snapshot.children) {
                        val quest = dataSnapShot.getValue(Tasks::class.java)
                        val dateForGet = quest!!.deadLine.split("-").toTypedArray()
                        //여기서 파이어베이스의 과제 정보 중 자신의 이름이 등록된 과제들을 모두 추가해도록 해줍니다.
                        if (quest!!.userName.equals(App.name) && quest!!.dayOfWeek.equals(ForDayOfWeek2)&&dateForGet[2].equals(ForDate)) {
                            App.questList.add(quest)
                        }
                    }

                }

            })

        }



        getTasksList(App.DayQuest)
        //파이어베이스에서 과제 리스트 데이터를 받아와서 앱의 전역 변수인 App.questList에 넣어주는 함수

        //앱의 전역 변수인 App.questList에 있는 과제 목록들을 리사이클러 뷰에 뿌려주는 함수
        val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
        root.rv_Tasks.adapter = adapter!!
        root.rv_Tasks.layoutManager= LinearLayoutManager(context)


        val btn1 = root.sun // btn1에 과제목록 레이아웃에 있는 일요일 버튼을 대입해 줍니다.
        btn1.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 일요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 1
            root.TodayINFO.text = root.sun.text.toString()
            getTasksList(1) //
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)

        })

        val btn2 = root.mon // btn1에 과제목록 레이아웃에 있는 월요일 버튼을 대입해 줍니다.
        btn2.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 월요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 2
            root.TodayINFO.text = root.mon.text.toString()
            getTasksList(2)
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)

            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)
        })

        val btn3 = root.tue // btn3에 과제목록 레이아웃에 있는 화요일 버튼을 대입해 줍니다.
        btn3.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 화요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 3
            root.TodayINFO.text = root.tue.text.toString()
            getTasksList(3)
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)

        })

        val btn4 = root.wed // btn4에 과제목록 레이아웃에 있는 수요일 버튼을 대입해 줍니다.
        btn4.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 수요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 4
            root.TodayINFO.text = root.wed.text.toString()
            getTasksList(4)
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)

            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)
        })

        val btn5 = root.thu // btn5에 과제목록 레이아웃에 있는 목요일 버튼을 대입해 줍니다.
        btn5.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 목요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 5
            root.TodayINFO.text = root.thu.text.toString()
            getTasksList(5)
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)

        })

        val btn6 = root.fri // btn6에 과제목록 레이아웃에 있는 금요일 버튼을 대입해 줍니다.
        btn6.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 금요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 6
            root.TodayINFO.text = root.fri.text.toString()
            getTasksList(6)
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)

        })

        val btn7 = root.sat // btn7에 과제목록 레이아웃에 있는 토요일 버튼을 대입해 줍니다.
        btn7.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 토요일 버튼을 누르면 일어나는 일들을 담고 있습니다.
            App.DayQuest = 7
            root.TodayINFO.text = root.sat.text.toString()
            getTasksList(7)

            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)

            val intent1 = Intent(context, ForLoading::class.java)
            startActivity(intent1)
        })

        val btnForUpdate = root.ButtonForUpdate // 상수 btnForUpdate에 과제목록 창에 있는 새로고침 버튼을 대입해 줍니다.
        btnForUpdate.setOnClickListener{
            getTasksList(App.DayQuest)
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
            Toast.makeText(activity, "새로고침 되었습니다.", Toast.LENGTH_LONG).show()
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        btn_plus.setOnClickListener()
        {
            val action = AssignmentManagementFragmentDirections.actionNavigationDashboardToAdditionFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }

}