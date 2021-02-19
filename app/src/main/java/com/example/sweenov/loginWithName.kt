package com.example.sweenov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_with_name.*
import java.util.*

class loginWithName : AppCompatActivity() {
    //로그인 창을 위한 액티비티
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_name)
        App.m0.pause()
        App.m1.pause()
        App.m2.pause()
        App.m3.pause()

        buttonForLogin.setOnClickListener{//로그인이라고 써져 있는 버튼을 누르면 실행되는 곳
            App.name = nameForLogin.text.toString() //로그인을 할 때 자신의 이름을 입력하면 그 이름을 전역함수 App.name에 대입해 줍니다.
            App.DayQuest = todayGenerator()

            //아래 코드를 통해서 로그인 창에서 로딩 창으로 넘어가게 해준다
            val intent1 = Intent(this, ForLoading::class.java)
            startActivity(intent1)
        }
    }


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
    fun todayGenerator() :Int{

        val dateArray = timeGenerator().split("-").toTypedArray()
        val cal = Calendar.getInstance()
        cal[dateArray[0].toInt(), dateArray[1].toInt() - 1] = dateArray[2].toInt()
        // 요일 확인(일요일:1, 월요일:2, ... ,토요일:7)

        var dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)


        return dayOfWeek
    }
}