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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login_with_name.*
import kotlinx.android.synthetic.main.fragment_addition.*
import kotlinx.android.synthetic.main.list_item.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AdditionFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

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
                    _,i,i2,i3 -> et_date.setText("${i}-${i2+1}-${i3}")

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

                            registerTask(App.name, et_subject.text.toString(), et_title.text.toString(), et_date.text.toString(),et_time.text.toString())
                            //사용자가 앱에서 입력한 과제 정보들을 파이어베이스에 추가하는 함수를 실행


                            Toast.makeText(activity, "과제가 추가되었습니다.", Toast.LENGTH_SHORT).show() //과제가 추가되었다는 메시지를 출력
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


        App.m0.pause()
        App.m1.pause()
        App.m2.pause()
        App.m3.pause()

    }




    private fun registerTask(userName: String, subjectName: String, assignmentName: String, deadLine: String, closingTime:String) {
        //이곳은 파이어베이스에 과제 정보를 추가해주는 함수입니다.

        var stringForData = "$deadLine-$closingTime-$assignmentName"

        val dateArray = deadLine.split("-").toTypedArray()
        val cal = Calendar.getInstance()
        cal[dateArray[0].toInt(), dateArray[1].toInt() - 1] = dateArray[2].toInt()
        // 요일 확인(일요일:1, 월요일:2, ... ,토요일:7)

        var dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        var dayOfWeekString : String = "ha"
        if(dayOfWeek == 1) dayOfWeekString = "sun"
        else if (dayOfWeek == 2) dayOfWeekString = "mon"
        else if (dayOfWeek == 3) dayOfWeekString = "tue"
        else if (dayOfWeek == 4) dayOfWeekString = "wed"
        else if (dayOfWeek == 5) dayOfWeekString = "thu"
        else if (dayOfWeek == 6) dayOfWeekString = "fri"
        else if (dayOfWeek == 7) dayOfWeekString = "sat"

        databaseReference = FirebaseDatabase.getInstance().getReference(userName).child(stringForData)
        //파이어베이스로 접속해서 함수의 인자로 받은 사용자 이름 userName을 최상위 폴더명으로 인지하고
        //stringForData라는 이름을 그 다음 하위 폴더명으로 인식하여
        //그 userName-stringForData 폴더를 databaseReference라는 변수에 넣어 줍니다.


        var hashMap: HashMap<String, String> = HashMap()// 이것은 파이어베이스에 정보를 입력할 때 필요한 함수로 예상됩니다, 형식(틀)과 그에 맞는 정보를 String 값으로 받는 다는 의미로 추정됩니다

        hashMap.put("subjectName", subjectName)         // subjectName이라는 틀을 만들고 그 안에 인자로 받은 과목 명 값을 넣어 줍니다.
        hashMap.put("assignmentName", assignmentName)   // assignmentName이라는 틀을 만들고 그 안에 인자로 받은 과제 제목 값을 넣어 줍니다.
        hashMap.put("deadLine", deadLine)               // deadLine이라는 틀을 만들고 그 안에 인자로 받은 마감일 정보를 넣어 줍니다.
        hashMap.put("closingTime", closingTime)         // closingTIme이라는 틀을 만들고 그 안에 인자로 받은 마감시간 정보를 넣어 줍니다.
        hashMap.put("userName", userName)               // userName이라는 틀을 만들고 그 안에 인자로 받은 사용자 이름 값을 넣어 줍니다.
        hashMap.put("dayOfWeek", dayOfWeekString)



        databaseReference.setValue(hashMap)
        //바로 위 코드에서 만들어진 틀과 그에 맞게 들어간 정보 모두를
        //파이어베이스의 userName-stringForData라는 이름의 폴더에 등록해 줍니다.

    }

}