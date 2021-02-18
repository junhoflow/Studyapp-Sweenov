package com.example.sweenov.ui.studymode

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweenov.App
import com.example.sweenov.ForLoading
import com.example.sweenov.R
import com.example.sweenov.ui.assignment_management.TaskAdapter
import kotlinx.android.synthetic.main.fragment_a_m.view.*
import kotlinx.android.synthetic.main.fragment_studymode.view.*
import kotlin.concurrent.thread

class StudyModeFragment : Fragment() {

    private lateinit var studyModeViewModel: StudyModeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        studyModeViewModel =
                ViewModelProvider(this).get(StudyModeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_studymode, container, false)

        App.m0 = MediaPlayer.create(context, R.raw.firesound2)
        App.m1 = MediaPlayer.create(context, R.raw.rain)
        App.m2 = MediaPlayer.create(context, R.raw.naturesound)
        App.m3 = MediaPlayer.create(context, R.raw.office)


        val btn0 = root.toggleButton0 // btn1에 과제목록 레이아웃에 있는 월요일 버튼을 대입해 줍니다.
        btn0.setOnClickListener(View.OnClickListener {
            if(btn0.isChecked == true) {
                Toast.makeText(activity, "장작 타는 소리 ON!", Toast.LENGTH_LONG).show()
                App.m0.start()
                App.m0.setOnCompletionListener {
                    App.m0.start()
                }

            }else{

                App.m0.pause()
            }
        })


        val btn1 = root.toggleButton // btn1에 과제목록 레이아웃에 있는 월요일 버튼을 대입해 줍니다.
        btn1.setOnClickListener(View.OnClickListener {
            if(btn1.isChecked == true) {
                Toast.makeText(activity, "빗소리 ON!", Toast.LENGTH_LONG).show()
                App.m1.start()
                App.m1.setOnCompletionListener {
                    App.m1.start()
                }
            }else{
                App.m1.pause()

            }
        })

        val btn2 = root.toggleButton2 // btn1에 과제목록 레이아웃에 있는 월요일 버튼을 대입해 줍니다.
        btn2.setOnClickListener(View.OnClickListener {
            if(btn2.isChecked == true) {
                Toast.makeText(activity, "자연 소리 ON!", Toast.LENGTH_LONG).show()
                App.m2.start()
                App.m2.setOnCompletionListener {
                    App.m2.start()
                }

            }else{

                App.m2.pause()
            }
        })

        val btn3 = root.toggleButton3 // btn1에 과제목록 레이아웃에 있는 월요일 버튼을 대입해 줍니다.
        btn3.setOnClickListener(View.OnClickListener {
            if(btn3.isChecked == true) {
                Toast.makeText(activity, "사무실 소리 ON!", Toast.LENGTH_LONG).show()
                App.m3.start()
                App.m3.setOnCompletionListener {
                    App.m3.start()
                }

            }else{

                App.m3.pause()
            }
        })
        val hour = String.format("%02d", App.total/3600)
        val minute = String.format("%02d", App.total/60)
        val second = String.format("%02d", App.total%60)
        root.textTimer.text = "$hour:$minute:$second"

        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                val hour2 = String.format("%02d", App.total/3600)
                val minute2 = String.format("%02d", App.total/60)
                val second2 = String.format("%02d", App.total%60)

                root.textTimer.text = "$hour2:$minute2:$second2"
            }

        }
        handler?.sendEmptyMessage(0)

        val btnForStart = root.toggleButton4Time
        btnForStart.setOnClickListener {
            
            if(btnForStart.isChecked == true) {

                root.layout.setBackgroundColor(Color.BLACK)
                root.todayTimer.setTextColor(Color.WHITE)
                Toast.makeText(activity, "스터디 모드 ON!", Toast.LENGTH_LONG).show()
                App.started = true
                App.ForTime = 1

                thread(App.started == true) {
                    while (App.started){
                        Thread.sleep(1000)
                        if(App.started){
                            App.total = App.total + 1
                            handler?.sendEmptyMessage(0)
                        }
                    }
                }


            }else{
                if(App.ForTime==1)Toast.makeText(activity, "스터디모드가 종료되었습니다.", Toast.LENGTH_LONG).show()
                root.layout.setBackgroundColor(Color.WHITE)
                root.todayTimer.setTextColor(Color.parseColor("#AD010002"))
                App.started = false
                App.ForTime = 0
            }



        }




        return root
    }
}