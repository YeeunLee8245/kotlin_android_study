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
import android.os.SystemClock
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.IconCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            // 시간을 밀리세컨트 단위로 구하기
            val now = System.currentTimeMillis()
            textView.text = "버튼 클릭 : $now"
        }

        // while문을 도느라 화면 메인 액티비티 처리를 전혀 하지 못함
//        while(true){
//            SystemClock.sleep(100)
//            val now2 = System.currentTimeMillis()
//            Log.d("test","while 문: $now2")
//        }

//        val thread1 = object : Thread(){
//            override fun run() {
//                super.run()
//                while(isRunning){
//                    SystemClock.sleep(100)
//                    val now2 = System.currentTimeMillis()
//                    Log.d("test","Thread : $now2")
//                    // 화면 관련 작업을 개발자가 생성된 쓰레드 내에서 하기(버전 8 아래의 하위 버전에서는 X)
////                    textView2.text = "Thread : $now2"
//                }
//            }
//        }
//        thread1.start()

        // 코틀린에서는 Thread라는 고차함수로 쓰레드 운영이 지원된다(주로 이것을 쓴다)
        // 람다로 고차함수 셋팅
        thread { // start 메소드 따로 호출하지 않아도 됨
            while(isRunning){
                SystemClock.sleep(100)
                val now2 = System.currentTimeMillis()
                Log.d("test","Thread : $now2")
            }
        }
    }

    // 메인 액티비티가 종료될 때 Thread도 종료시킴
    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

}
