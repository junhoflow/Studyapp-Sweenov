package com.example.sweenov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import com.example.sweenov.ui.dashboard.Tasks
import com.google.firebase.database.*

class ForLoading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_loading)

        getTasksList()

        SystemClock.sleep(500)
        val intent1 = Intent(this, MainActivity::class.java)
        startActivity(intent1)
        finish()
    }



    fun getTasksList() {
        //파이어베이스에서 과제 list를 가져오는 함수
        //현재는 단순하게 자신의 이름 폴더 명에 있는 과제들을 모두 가져오도록 해주어
        //날짜 별로 과제를 가져오지는 못 합니다.

        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(App.name)

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ForLoading, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                App.questList.clear()

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val quest = dataSnapShot.getValue(Tasks::class.java)
                    //여기서 파이어베이스의 과제 정보 중 자신의 이름이 등록된 과제들을 모두 추가해도록 해줍니다.
                    if (quest!!.userName.equals(App.name)) {
                        App.questList.add(quest)
                    }
                }

            }

        })
    }
}