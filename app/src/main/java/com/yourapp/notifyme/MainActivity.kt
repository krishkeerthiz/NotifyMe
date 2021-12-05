package com.yourapp.notifyme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {
    private lateinit var notifyManager : NotificationManager
    private val NOTIFICATION_CHANNEL = "com.yourapp.notifyme.NOTIFICATION_CHANNEL"
    private val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//            var soundUri: Uri =
//                Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.packageName + "/" + R.raw.mysound)
//
        val notifyButton = findViewById<Button>(R.id.notify_button)
        val updateButton = findViewById<Button>(R.id.update_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        notifyButton.setOnClickListener {
            sendNotification()
        }

        updateButton.setOnClickListener {
            updateNotification()
        }

        cancelButton.setOnClickListener {
            cancelNotification()
        }

        createNotificationChannel()
    }

    private fun sendNotification(){
        val notificationBuilder = getNotificationBuilder()
        notifyManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun updateNotification(){

    }

    private fun cancelNotification(){

    }

    private fun createNotificationChannel(){
        notifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channel = NotificationChannel(NOTIFICATION_CHANNEL, "Test Notification", NotificationManager.IMPORTANCE_HIGH)
            channel.lightColor = Color.WHITE
            channel.enableVibration(true)
            channel.description = "Notification from Test"
            channel.enableLights(true)
            notifyManager.createNotificationChannel(channel)
        }

    }

    private fun getNotificationBuilder() : NotificationCompat.Builder {
        val intent = Intent (this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("You've been notified!")
            .setContentText("This is your notification text.")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setLights(Color.WHITE, 3000, 3000)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))


        return builder
    }
}