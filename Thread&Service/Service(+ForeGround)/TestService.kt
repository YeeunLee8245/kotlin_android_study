package kr.co.yeaeun.viewbasic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class TestService : Service() {
    var isRunning = false
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // 서비스가 가동될 때 호출되는 메서드드
   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("test", "서비스 가동")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("service", "service", NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this,"service")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setContentTitle("서비스 가동")
            builder.setContentText("서비스 가동 중입니다.")
            val notification = builder.build()

            // manager을 사용하여 notify 호출X
            // 알림 메시지를 Foreground 서비스를 위해 표시한다.
            // notifycation id는 0을 주어선 X
            startForeground(10, notification)
        }

        isRunning = true

        thread {
            while(isRunning){
                SystemClock.sleep(500)
                val now= System.currentTimeMillis()
                Log.d("test","Service : $now")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    // 서비스가 중지되고 소멸될 때 호출되는 메서드
    override fun onDestroy() {
        super.onDestroy()
        Log.d("test","서비스 중지")

        isRunning = false
    }
}
