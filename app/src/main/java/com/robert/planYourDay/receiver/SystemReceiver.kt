package com.robert.planYourDay.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SystemReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneModeEnabled=intent?.getBooleanExtra("state",false)?:return

        if(isAirPlaneModeEnabled){
            Toast.makeText(context,"airplane mode is enabled", Toast.LENGTH_LONG).show()
        }

        else{
            Toast.makeText(context,"airplane mode is not enabled", Toast.LENGTH_LONG).show()

        }
    }
}