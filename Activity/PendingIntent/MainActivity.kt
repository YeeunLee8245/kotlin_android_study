package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            // 노티 메시지 세팅(채널 이름, 표시할 이름)
            val builder1 = getNotificationBuilder("pending", "pending intent")
            builder1.setContentTitle("notification 1")
            builder1.setContentText("알림 메시지 1입니다")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)
            val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)
            // 상단바 알림 터치시 알림 자동 사라짐
            builder1.setAutoCancel(true)

            // 메시지를 터치하면 실행할 Activity를 관리할 Intent 생성
            val intent1 = Intent(this, NotificationActivity1::class.java)
            intent1.putExtra("data1", 100) // 데이터 전송송
            intent1.putExtra("data2", 200)
            //(this, 해당 엑티비티에서 메시지 확인할 메시지 번호, intent, 액티비티 실행시 화면 갱신)
            val pending1 = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            builder1.setContentIntent(pending1)

            // Action 설정
            val intent2 = Intent(this, NotificationActivity3::class.java)
            val pending2 = PendingIntent.getActivity(this, 100, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder2 = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_compass, "Action 1", pending2)
            // Action 객체 생성성
            val action2 = builder2.build()
            builder1.addAction(action2)

            val intent3 = Intent(this, NotificationActivity4::class.java)
            val pending3 = PendingIntent.getActivity(this, 100, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder3 = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_agenda, "Action 2", pending3)
            // Action 객체 생성성
            val action3 = builder3.build()
            builder1.addAction(action3)

            // 메시지 띄우기
            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        button2.setOnClickListener{
            // 노티 메시지 세팅(채널 이름, 표시할 이름)
            val builder1 = getNotificationBuilder("pending", "pending intent")
            builder1.setContentTitle("notification 2")
            builder1.setContentText("알림 메시지 2입니다")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)
            val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)

            // 메시지를 터치하면 실행할
            val intent1 = Intent(this, NotificationActivity2::class.java)
            //(this, 해당 엑티비티에서 메시지 확인할 메시지 번호, intent, 액티비티 실행시 화면 갱신)
            val pending1 = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            builder1.setContentIntent(pending1)

            // 메시지 띄우기
            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification) // 알림 메시지 id도 함께 설정
        }
    }
    fun getNotificationBuilder(id: String, name: String) : NotificationCompat.Builder{
        // os 버전별로 나누기
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ // 8.0 버전 이상
            // 메시지 관리 객체 추출
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // 채널 객체 생성
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            // LED 셋팅
            channel.enableLights(true)
            channel.lightColor = Color.RED
            // 진동 사용 여부
            channel.enableVibration(true)
            // 노티 채널 등록
            manager.createNotificationChannel(channel)
            // 노티피케이션 사용을 위한 객체 생성
            val builder1 = NotificationCompat.Builder(this, id)
            return builder1
        }else {// 8.0 보다 낮은 버전이라면
            val builder2 = NotificationCompat.Builder(this)
            return builder2
        }
    }
}
