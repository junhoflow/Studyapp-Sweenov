package com.example.sweenov.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "과제 관리 들어갈 부분!"
    }
    val text: LiveData<String> = _text
}