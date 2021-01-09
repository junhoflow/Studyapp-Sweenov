package com.example.sweenov

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.sweenov.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_assignment__add.*
import java.util.*

class Assignment_Add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment__add)

        et_date.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener{
                _,i,i2,i3 -> et_date.setText("${i}년 ${i2+1}월 ${i3}일")
            }

            var picker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                listener,year, month, day)
            picker.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
            picker.show()
        }

        et_time.setOnClickListener{
            var calendar = Calendar.getInstance()
            var hour = calendar.get(Calendar.HOUR)
            var min = calendar.get(Calendar.MINUTE)

            var listener = TimePickerDialog.OnTimeSetListener{
                _,i,i2 -> et_time.setText("${i}시 ${i2}분")
            }

            var picker = TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog,
                listener,hour,min,false)
            picker.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
            picker.show()

        }
        btn_add.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}