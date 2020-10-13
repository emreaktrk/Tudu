package com.akturk.tudu.add

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            Handler(it.mainLooper).post {
                Toast.makeText(it, "Alarm", Toast.LENGTH_LONG).show()
            }
        }
    }
}