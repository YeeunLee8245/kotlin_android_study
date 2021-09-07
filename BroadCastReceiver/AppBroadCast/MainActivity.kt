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

    val br = TestReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // OS 버전 8.0 이상 부터는 코드를 통해 등록하고 해제해야한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // BroadCast 이름 설정
            val filter = IntentFilter("yeeun.BroadCast")
            //BroadCast 등록
            registerReceiver(br,filter)

        }

        button.setOnClickListener{
            // 같은 app에 있는 브로드캐스트를 호출할 때는 파일 이름으로 호출 가능
//            val brIntent = Intent(this, TestReceiver::class.java)
//            sendBroadcast(brIntent)
            // 다른 app에 있는 브로드캐스트를 호출할 때는 반드시 브로드캐스트 이름을 지정하여 호출해야함
            val brIntent = Intent(this, TestReceiver::class.java)
            sendBroadcast(brIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // BroadCast Reciver을 해제
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            unregisterReceiver(br)
        }
    }
}
