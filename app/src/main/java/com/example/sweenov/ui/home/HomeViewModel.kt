package com.example.sweenov.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "스터디모드 들어갈 부분"
    }
    val text: LiveData<String> = _text
}