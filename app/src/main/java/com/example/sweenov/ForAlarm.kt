package com.example.sweenov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class ForAlarm : AppCompatActivity() {

    private val mNotificationTime2 = Calendar.getInstance().timeInMillis + App.ForAlarm //Set after 5 seconds from the current time.
    private var mNotified2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_alarm)

        if (!mNotified2) {
            NotificationUtils().setNotification(mNotificationTime2, this)
        }
        val intent1 = Intent(this, MainActivity::class.java)
        startActivity(intent1)
    }
}