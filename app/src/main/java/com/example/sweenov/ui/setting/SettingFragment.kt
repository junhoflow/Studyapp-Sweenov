package com.example.sweenov.ui.setting

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sweenov.AlarmSetting
import com.example.sweenov.BuildConfig
import com.example.sweenov.R
import com.example.sweenov.loginWithName
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    private lateinit var settingViewModel: SettingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, // 화면 시작될 때 inflater, layoutinflater를 통해 res/layout 형식을 가져와 화면을 구성한다
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingViewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
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

        alarmsetting_btn.setOnClickListener{
            val intent1 = Intent(context, AlarmSetting::class.java)
            startActivity(intent1)
        }



        //sharedpreference로 설정값 저장

        version.text = BuildConfig.VERSION_NAME


        mailing.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/text"
            val address = arrayOf("developer email")
            email.putExtra(Intent.EXTRA_EMAIL, address)
            email.putExtra(Intent.EXTRA_SUBJECT, " Sweenov 개발자에게 메일보내기")
            email.putExtra(
                Intent.EXTRA_TEXT,
                "앱 버전: ${BuildConfig.VERSION_NAME}\nAndroid(SDK): ${Build.VERSION.SDK_INT}\n 핸드폰 기종: \n 내용:"
            )
            startActivity(email)
        }

        logout.setOnClickListener{
            val intent1 = Intent(context, loginWithName::class.java)
            startActivity(intent1)
        }



    }
}