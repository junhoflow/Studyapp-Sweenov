package com.example.sweenov

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_for_receiving_alarm.*

class ForReceivingAlarm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_receiving_alarm)
        App.mforalarm = MediaPlayer.create(this, R.raw.returntolove)
        App.mforalarm.start()
        buttonForStop.setOnClickListener {

            App.mforalarm.pause()

            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)

        }

    }
}