package com.example.sweenov

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_alarmsetting.*
import java.util.*
import kotlinx.android.synthetic.main.activity_alarmsetting.isAlarm_switch as isAlarm_switch1

class AlarmSetting : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)

    //private val mNotificationTime = Calendar.getInstance().timeInMillis + App.ForAlarm //Set after 5 seconds from the current time.
    //private var mNotified = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmsetting)

        //if (!mNotified) {
            //NotificationUtils().setNotification(mNotificationTime, this@AlarmSetting)
        //}

        isAlarm_switch1.setOnCheckedChangeListener{ _, isChecked ->
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect = VibrationEffect.createOneShot(100, 50)
            if(isChecked){
                vibrator.vibrate(vibrationEffect)
                Toast.makeText(this, "알람 ON!", Toast.LENGTH_LONG).show()

            } else{
                vibrator.vibrate(vibrationEffect)
                Toast.makeText(this, "알람 OFF!", Toast.LENGTH_LONG).show()

            }
        }

        vibrate_switch.setOnCheckedChangeListener{ _, isChecked ->
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            val timings = longArrayOf(0, 70, 30, 70)
            val vibrationEffect = VibrationEffect.createWaveform(timings, -1)
            if(isChecked){
                vibrator.vibrate(vibrationEffect)
            } else{
                vibrator.cancel();
            }

        }
    }

}