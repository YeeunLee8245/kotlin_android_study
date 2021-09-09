package kr.co.yeaeun.viewbasic

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var playBtn: ImageView
    lateinit var stopBtn: ImageView
    var progressBar: ProgressBar? = null
    lateinit var titleView: TextView
    var filePath: String? = null
    var runThread = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playBtn = findViewById(R.id.lab1_play)
        stopBtn = findViewById(R.id.lab1_stop)
        progressBar = findViewById(R.id.lab1_progress)
        titleView = findViewById(R.id.lab1_title)
        playBtn.setOnClickListener(this)
        stopBtn.setOnClickListener(this)
        titleView.setText("music.mp3")
        stopBtn.setEnabled(false)
        // Android 10이상에서는 외부저장소에 대해 Scoped storage 모드로 동작함
        // 기존 외부저장소의 공통 저장 공간이 모두 사라지고 개별 앱공간이 격리된다는 뜻
        filePath = getExternalFilesDir(null)?.absolutePath+"/music.mp3"
        Log.d("경로: ", filePath as String)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        }
        // Activity쪽 Receiver를 시스템에 등록: 리시버를 AndroidManifest.xml 파일에 등록한 것이 나닌
        // 코드에서 (브로드캐스트)리시버 등록 함수를 이용한 것
        registerReceiver(receiver, IntentFilter("com.example.PLAY_TO_ACTIVITY"))
        // intent와 startService() 함수로 Service 구동
        val intent = Intent(this, PlayService::class.java)
        intent.putExtra("filePath", filePath)
        startService(intent)
    }

    // progressbar 증가를 위한 class
    inner class ProgressThread : Thread() {
        override fun run() {
            while (runThread) {
                progressBar!!.incrementProgressBy(1000)
                SystemClock.sleep(1000)
                if (progressBar!!.progress == progressBar!!.max) {
                    runThread = false
                }
            }
        }
    }

    // Service로부터 데이터를 받기 위한 Receiver
    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val mode = intent.getStringExtra("mode")
            if (mode != null) {
                if (mode == "start") { // 최초로 Service가 구동된 상태라면 duration 설정만 획득
                    val duration = intent.getIntExtra("duration", 0)
                    progressBar!!.max = duration
                    progressBar!!.progress = 0
                } else if (mode == "stop") { // Service 쪽에서 음악 플레이가 종료된 상황
                    runThread = false
                } else if (mode == "restart") { // activity가 다시 시작
                    // duration: 전체 플레이 시간
                    val duration = intent.getIntExtra("duration", 0) // Service쪽에서 음악을 play 하고있는 도중일 때 max 시간 설정을 위해
                    val current = intent.getIntExtra("current", 0) // 현재 play 위치를 획득 후 ProgressBar의 값으로 설정하기 위해
                    progressBar!!.max = duration
                    progressBar!!.progress = current
                    runThread = true
                    // ProgressBar의 값을 증가시키는 Thread
                    val thread: ProgressThread = ProgressThread()
                    thread.start()
                    // 화면 button 제어
                    playBtn!!.isEnabled = false
                    stopBtn!!.isEnabled = true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity 종료시 Receiver 등록 해제
        unregisterReceiver(receiver)
    }

    override fun onClick(v: View) {
        if (v === playBtn) { // Service쪽 Receiver 실행
            val intent = Intent("com.example.PLAY_TO_SERVICE")
            intent.putExtra("mode", "start")
            sendBroadcast(intent)
            runThread = true
            val thread: ProgressThread = ProgressThread()
            thread.start()
            playBtn!!.isEnabled = false
            stopBtn!!.isEnabled = true
        } else if (v === stopBtn) {
            val intent = Intent("com.example.PLAY_TO_SERVICE")
            intent.putExtra("mode", "stop")
            sendBroadcast(intent)
            runThread = false
            progressBar!!.progress = 0
            playBtn!!.isEnabled = true
            stopBtn!!.isEnabled = false
        }
    }
}