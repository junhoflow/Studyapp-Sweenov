package com.example.sweenov

import android.app.Application
import com.example.sweenov.ui.dashboard.Tasks
import com.google.android.gms.tasks.Task

class App : Application() {
    companion object
    {


        var questList : ArrayList<Tasks> = arrayListOf(
            Tasks( "새로고침 해주세요", "", "", "",""),
            Tasks( "", "", "", "","")
        )

        //var todoList = arrayListOf<ThingsTodo>(
        //  ThingsTodo("엄마카드로 학원비 결제하기", "200p"),
        //  ThingsTodo("구몬 수학 p.33까지 학습 후 채점하기", "500p"),
        //  ThingsTodo("구몬 수학 p.33까지 학습 후 채점하기", "500p"),
        //  ThingsTodo("구몬 수학 p.33까지 학습 후 채점하기", "500p")
        // )
    }
}