package com.robert.planYourDay.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.robert.planYourDay.util.sendNotification


class TimerReceiver : BroadcastReceiver() {
    val CHANNEL_ID = "com.robert.planYourDay.TODO"
    val CHANNEL_NAME = "my plan"


    override fun onReceive(context: Context?, intent: Intent?) {
        val msg=intent?.getStringExtra("msg")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val imp = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, imp)


            val notificationManager = context?.getSystemService(
                Context.NOTIFICATION_SERVICE
            )
                    as NotificationManager

            notificationManager.createNotificationChannel(notificationChannel)
            if (msg != null) {
                notificationManager.sendNotification(context,msg)
            }
            else {
                Log.e("TimerReceiver","no msg have been passed here")
            }

        }
    }
}