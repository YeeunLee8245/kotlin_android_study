package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.app.*
import android.content.*
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

    // 접속한 서비스 객체
    var ipcService:TestService? = null

    // 서비스 접속을 관리하는 객체
    val connection = object : ServiceConnection{
        // 서비스에 접속이 성공했을 때 호출되는 메서드
        // 2번 인자: service의 onBind 메서드가 반환하는 객체를 받는다
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 서비스를 추출한다
            val binder = service as TestService.LocalBinder // LocalBinder 타입으로 형변환
            ipcService = binder.getService()
        }
        // 서비스 접속을 해제했을 때
        override fun onServiceDisconnected(name: ComponentName?) {
            ipcService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 서비스가 가동중이 아니라면 서비스를 가동한다.
        // 패키지 이름을 포함한 class 이름을 넣어주어야함
        val chk = isServiceRunning("kr.co.yeaeun.viewbasic.TestService")
        val serviceIntent = Intent(this, TestService::class.java)
        if(chk == false){ // 해당 서비스가 실행되고 있지 않다면
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                startForegroundService(serviceIntent)
            }else{
                startService(serviceIntent)
            }
        }
        // 서비스에 접속한다.
        // connection 셋팅
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener {
            var value =ipcService?.getNumber()
            textView.text = "value : $value"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 접속한 서비스에 접속을 해제한다.(서비스를 종료하는 것 아님!!)
        unbindService(connection)
    }

    // 서비스 실행 여부를 검사하는 메서드
    fun isServiceRunning(name: String) : Boolean{
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        // 현재 앱에서 실행 중인 서비스들을 가져온다(deprecate 되기 전엔 OS 전체에서 실행 중인 서비스였음)
        // 보안상의 문제 때문에 메소드가 수정되었다고 알리기 위해 deprecate 된 것이라서 삭제는 안 될듯?
        val serviceList = manager.getRunningServices(Int.MAX_VALUE)

        for(serviceInfo in serviceList){
            // 서비스의 이름이 원하는 이름인가?
            if(serviceInfo.service.className == name){
                return true
            }
        }
        return false

    }

}
