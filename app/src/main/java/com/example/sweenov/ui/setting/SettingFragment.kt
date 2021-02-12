package com.example.sweenov.ui.setting

import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sweenov.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreateView(
            inflater: LayoutInflater, // 화면 시작될 때 inflater, layoutinflater를 통해 res/layout 형식을 가져와 화면을 구성한다
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingViewModel =
                ViewModelProvider(this).get(SettingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_setting, container, false) //inflate를 통해 res/layout/fragment_notifications 레이아웃을 가져옴
        val textView: TextView = root.findViewById(R.id.text_notifications)
        settingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleButton2.setOnCheckedChangeListener{_, isChecked ->
            val vibrator = context?.getSystemService(VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect = VibrationEffect.createOneShot(100, 50)
            if(isChecked){
                vibrator.vibrate(vibrationEffect)
                Toast.makeText(activity, "알람 ON!", Toast.LENGTH_LONG).show()
            }else{
                vibrator.vibrate(vibrationEffect)
                Toast.makeText(activity, "알람 OFF!", Toast.LENGTH_LONG).show()
            }
        }

        vibrate_switch.setOnCheckedChangeListener{ _, isChecked ->
            val vibrator = context?.getSystemService(VIBRATOR_SERVICE) as Vibrator
            val timings = longArrayOf(0,70,30,70)
            val vibrationEffect = VibrationEffect.createWaveform(timings,-1)
            if(isChecked){
                vibrator.vibrate(vibrationEffect)
            } else{
                vibrator.cancel();
            }

        }




    }
}