package com.example.sweenov

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_addition.*
import java.util.*

class AdditionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_date.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener{
                    _,i,i2,i3 -> et_date.setText("${i}년 ${i2+1}월 ${i3}일")
            }

            var picker = DatePickerDialog(
                this!!.requireActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
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

            var picker = TimePickerDialog(activity,android.R.style.Theme_Holo_Light_Dialog,
                listener,hour,min,false)
            picker.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
            picker.show()
        }

        btn_add.setOnClickListener{
            val inputSubject = et_subject.text.toString()
            val inputTitle = et_title.text.toString()
            val inputDate = et_date.text.toString()
            val inputTime = et_time.text.toString()

            if(inputSubject.isNotEmpty())
            {
                if(inputTitle.isNotEmpty())
                {
                    if(inputDate.isNotEmpty())
                    {
                        if(inputTime.isNotEmpty())
                        {
                            Toast.makeText(activity, "과제가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                            val action = AdditionFragmentDirections.actionAdditionFragmentToNavigationDashboard()
                            Navigation.findNavController(view).navigate(action)
                        }
                        else
                        {
                            Toast.makeText(activity, "마감시간을 추가해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(activity, "마감날짜를 추가해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(activity, "제목을 추가해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(activity, "과목을 추가해주세요.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}