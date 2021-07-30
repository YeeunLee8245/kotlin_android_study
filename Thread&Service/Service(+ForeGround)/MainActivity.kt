package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // intent를 이용해 service 객체 생성(Activity 실행하는 것과 동일)
        val serviceIntent = Intent(this, TestService::class.java)

        button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                startForegroundService(serviceIntent)
            }else {
                // 서비스 가동
                startService(serviceIntent)
            }
        }

        button2.setOnClickListener{
            // 서비스 중지
            stopService(serviceIntent)
        }
    }

}
