package com.example.sweenov.ui.assignment_management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.Navigation
import com.example.sweenov.App
import com.example.sweenov.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class AssignmentManagementFragment : Fragment() {
    // 이곳은 과제 목록 창의 동작을 담당하는 프래그먼트 입니다.
    // 이곳에서의 코드가 과제 목록 레이아웃과 상호작용합니다.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //위 변수 root는 fragment_dashboard 라는 레이아웃과
        //지금 이 프래그먼트(DashboardFragment.kt)의 연결고리 역할을 하는 역할인 것 같습니다


        root.rv_Tasks.setHasFixedSize(true)//리사이클러뷰의 사이즈 설정과 관련된 코드인 것 같다


        getTasksList()
        //파이어베이스에서 과제 리스트 데이터를 받아와서 앱의 전역 변수인 App.questList에 넣어주는 함수

        //앱의 전역 변수인 App.questList에 있는 과제 목록들을 리사이클러 뷰에 뿌려주는 함수
        val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
        root.rv_Tasks.adapter = adapter!!
        root.rv_Tasks.layoutManager= LinearLayoutManager(context)


        val btn1 = root.sun // btn1에 과제목록 레이아웃에 있는 일요일 버튼을 대입해 줍니다.
        btn1.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 일요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.sun.text.toString()
            getTasksList() //
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })

        val btn2 = root.mon // btn1에 과제목록 레이아웃에 있는 월요일 버튼을 대입해 줍니다.
        btn2.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 월요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.mon.text.toString()
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })

        val btn3 = root.tue // btn3에 과제목록 레이아웃에 있는 화요일 버튼을 대입해 줍니다.
        btn3.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 화요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.tue.text.toString()
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })

        val btn4 = root.wed // btn4에 과제목록 레이아웃에 있는 수요일 버튼을 대입해 줍니다.
        btn4.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 수요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.wed.text.toString()
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })

        val btn5 = root.thu // btn5에 과제목록 레이아웃에 있는 목요일 버튼을 대입해 줍니다.
        btn5.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 목요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.thu.text.toString()
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })

        val btn6 = root.fri // btn6에 과제목록 레이아웃에 있는 금요일 버튼을 대입해 줍니다.
        btn6.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 금요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.fri.text.toString()
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })

        val btn7 = root.sat // btn7에 과제목록 레이아웃에 있는 토요일 버튼을 대입해 줍니다.
        btn7.setOnClickListener(View.OnClickListener {
            //이 괄호 안은 토요일 버튼을 누르면 일어나는 일들을 담고 있습니다.

            root.TodayINFO.text = root.sat.text.toString()
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)
        })


        val btnForUpdate = root.ButtonForUpdate // 상수 btnForUpdate에 과제목록 창에 있는 새로고침 버튼을 대입해 줍니다.
        btnForUpdate.setOnClickListener{
            getTasksList()
            val adapter = context?.let { it1 -> TaskAdapter(it1,App.questList) }
            root.rv_Tasks.adapter = adapter!!
            root.rv_Tasks.layoutManager= LinearLayoutManager(context)

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        btn_plus.setOnClickListener()
        {
            val action = AssignmentManagementFragmentDirections.actionNavigationDashboardToAdditionFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }



    fun getTasksList() {
        //파이어베이스에서 과제 list를 가져오는 함수
        //현재는 단순하게 자신의 이름 폴더 명에 있는 과제들을 모두 가져오도록 해주어
        //날짜 별로 과제를 가져오지는 못 합니다.

        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(App.name)



        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
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