package com.example.sweenov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_with_name.*

class loginWithName : AppCompatActivity() {
    //로그인 창을 위한 액티비티

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_name)

        buttonForLogin.setOnClickListener{
            App.name = nameForLogin.text.toString() //로그인을 할 때 자신의 이름을 입력하면 그 이름을 전역함수 App.name에 대입해 줍니다.
            val intent1 = Intent(this, MainActivity::class.java)

            startActivity(intent1)
        }
    }
}