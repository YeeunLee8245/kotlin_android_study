package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
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
import android.os.*
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
            // 현재 시간을 밀리세컨트 단위로 구하기
            val now = System.currentTimeMillis()
            textView.text = "버튼 클릭 : $now"
        }



        // 오래 걸리는 작업: 반드시 thread로 운영해주기
        isRunning = true
        thread{ // 내부에서 화면 변경이 필요할 때 handler를 사용해 요청
            while(isRunning){
                val now2 = System.currentTimeMillis()
                Log.d("test","Thread : $now2")
                SystemClock.sleep(500)

                // Thread를 상속받거나 runable을 구현한 Class를 넣어주기기
//               runOnUiThread(object : Thread(){
//                   // Main Thread가 처리하도록 요청함
//                   override fun run(){
//                       super.run()
//                       textView2.text = "runOnUiTread : $now2"
//                   }
//               })
                // 코틀린에선 runOnUiThread를 람다로도 처리 가능
                runOnUiThread {
                    textView2.text = "runOnUiThread : $now2"
                }

                SystemClock.sleep(500)

                runOnUiThread(object : Thread(){
                    // Main Thread가 처리하도록 요청함
                    override fun run(){
                        super.run()
                        textView2.text = "또 다른 작업업"
                   }
                })


            }
        }
    }
}
