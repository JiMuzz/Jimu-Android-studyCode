package com.example.studynote.android11


import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.studynote.R
import java.io.File


class MyService : Service() {
    private val TAG = "MyService"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        val CHANNEL_ONE_ID = "testid"
        val CHANNEL_ONE_NAME = "testname"
        var notificationChannel: NotificationChannel? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.setShowBadge(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jianshu.com"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notification: Notification = Notification.Builder(this).setChannelId(CHANNEL_ONE_ID)
                .setTicker("Nature")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("这是一个测试标题")
                .setContentIntent(pendingIntent)
                .setContentText("这是一个测试内容")
                .build()
        notification.flags = notification.flags or Notification.FLAG_NO_CLEAR
        startForeground(1, notification)

        Handler().postDelayed({

            getLocation()

        }, 8000)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")




        return super.onStartCommand(intent, flags, startId)


    }


    fun getLocation(): String {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return ""
        }

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        Log.e(TAG, "${location == null}")
        if (location != null) {
            Log.e(TAG, "${location.latitude}")
        }

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1f) { location -> Log.e(TAG, "${location.latitude}") }

        return location?.latitude.toString()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}