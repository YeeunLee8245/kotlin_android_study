package kr.co.yeaeun.viewbasic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class TestService : Service() {
    // 변수는 private으로 선언되기 때문에 Activity에서 접근 불가능
    // 따라서 메소드를 정의하여 변수에 접근하고 그 값을 반환하는 형태로 Activity에게 값을 전달할 수 있다.
    var isRunning = false
    var value = 0
    var binder = LocalBinder()

    // 외부(ex. Activity)에서 서비스에 접속하면 호출되는 메서드
    override fun onBind(intent: Intent): IBinder {
        return binder // ServiceConnection 클래스를 상속 받은 객체의 메소드 인자로 들어감
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
                value += 1
                Log.d("test","value : $value")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    // 서비스가 중지되고 소멸될 때 호출되는 메서드
    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    // 변수의 값을 반환하는 메서드
    fun getNumber(): Int{
        return value
    }

    // 접속하는 Activity에서 서비스를 추출하기 위해 사용하는 객체
    inner class LocalBinder : Binder(){
        fun getService() : TestService{
            // TestService의 클래스 주소값을 반환
            // 중첩 내부 클래스에서 외부 클래스의 객체를 사용할 때 레이블을 사용함
            // 레이블을 붙여주지 않으면 LocalBinder 객체가 반환됨
            return this@TestService // Activity에서 이 service 주소를 이용해 정의한 getNumber 메소드를 사용할 수 있음
        }
    }
}
