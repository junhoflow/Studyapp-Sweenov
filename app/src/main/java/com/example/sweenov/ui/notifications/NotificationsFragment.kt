package com.example.sweenov.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sweenov.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, // 화면 시작될 때 inflater, layoutinflater를 통해 res/layout 형식을 가져와 화면을 구성한다
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false) //inflate를 통해 res/layout/fragment_notifications 레이아웃을 가져옴
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ToggleButton btn = findViewById(R.id.toggleButton2)
        btn.setOnClickListener(){
            if(btn.isChecked){
                Toast.makeText(activity,"알람 ON", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(activity, "알람 OFF", Toast.LENGTH_LONG).show()
            }

        }

    }
}