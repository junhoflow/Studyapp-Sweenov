package com.example.sweenov

import android.app.Application
import com.example.sweenov.ui.dashboard.Tasks
import com.google.android.gms.tasks.Task

class App : Application() {
    //이곳은 프로젝트의 전역 변수 사용을 위한 곳입니다.
    //이곳에 전역 변수를 선언하면 다른 프래그먼트나 액티비티에서 "App.전역변수이름" 이런 식으로 사용할 수 있습니다.

    companion object
    {
        var name: String = ""
        var questList : ArrayList<Tasks> = arrayListOf(
            Tasks( "새로 고침 해주세요", "", "", "","")
        )
    }
}