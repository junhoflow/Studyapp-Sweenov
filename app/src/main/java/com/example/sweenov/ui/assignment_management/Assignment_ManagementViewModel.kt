package com.example.sweenov.ui.assignment_management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Assignment_ManagementViewModel : ViewModel() {
    //이곳은 앱 개발 초창기에 과제 관리 기능이 들어갈 부분이라는 것을 알려주기 위한 곳이었습니다.
    //이제 과제 관리 쪽에서는 쓰이지 않습니다.

    private val _text = MutableLiveData<String>().apply {
        value = "과제 관리 들어갈 부분!"
    }
    val text: LiveData<String> = _text

}