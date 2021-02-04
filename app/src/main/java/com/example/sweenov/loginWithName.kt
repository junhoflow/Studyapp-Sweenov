package com.example.sweenov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_with_name.*

class loginWithName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_name)

        buttonForLogin.setOnClickListener{
            App.name = nameForLogin.text.toString()
            val intent1 = Intent(this, MainActivity::class.java)

            startActivity(intent1)
        }
    }
}