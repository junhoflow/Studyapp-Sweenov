package com.example.sweenov

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val service = Intent(context, NotificationService::class.java)
    }
}