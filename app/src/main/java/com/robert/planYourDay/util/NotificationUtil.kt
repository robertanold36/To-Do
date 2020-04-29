package com.robert.planYourDay.util


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.robert.planYourDay.R
import com.robert.planYourDay.ui.my_plan.MyPlan

// Notification ID.
private const val NOTIFICATION_ID = 0


 fun NotificationManager.sendNotification(
     context: Context,
     msg:String
 ){

    val contentIntent=Intent(context,MyPlan::class.java)

    val contentPendingIntent=PendingIntent.getActivity(context, NOTIFICATION_ID,
                                                    contentIntent,PendingIntent.FLAG_CANCEL_CURRENT)

    val builder=NotificationCompat.Builder(
        context,
        "com.robert.planYourDay.TODO"
    )
        .setSmallIcon(R.drawable.emoji).setContentTitle("Hello There Its Time")
        .setContentText(msg).setContentIntent(contentPendingIntent).setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

         notify(NOTIFICATION_ID,builder.build())
}
