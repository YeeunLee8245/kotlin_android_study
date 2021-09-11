package kr.co.yeaeun.test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kr.co.yeaeun.test_aidl.IPlayService

class Lab20_2Activity : AppCompatActivity(), View.OnClickListener {
    lateinit var playBtn: ImageView
    lateinit var stopBtn: ImageView
    lateinit var progressBar: ProgressBar
    lateinit var titleView: TextView
    lateinit var filePath: String
    var runThread = false
    var pService: IPlayService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab202)
        playBtn = findViewById(R.id.lab2_play)
        stopBtn = findViewById(R.id.lab2_stop)
        progressBar = findViewById(R.id.lab2_progress)
        titleView = findViewById(R.id.lab2_title)
        playBtn.setOnClickListener(this)
        stopBtn.setOnClickListener(this)
        titleView.setText("music.mp3")
        stopBtn.setEnabled(false)
        playBtn.setEnabled(false)
        //filePath = Environment.getExternalStorageDirectory().absolutePath + "/Music/music.mp3"
        // Android 10이상에서는 외부저장소에 대해 Scoped storage 모드로 동작함
        // 기존 외부저장소의 공통 저장 공간이 모두 사라지고 개별 앱공간이 격리된다는 뜻
        filePath = getExternalFilesDir(null)?.absolutePath+"/music.mp3" // 서버쪽 앱 데이터를 사용해야되는데 클라이언트 데이터를 사용해서(이어짐)
        // 안되는듯 그래서 서버 앱에 데이터를 저장하려해봤지만 어째선지 Android/data/kr.co.yeaeun.test_aidl 폴더가 만들어지지 않는다.
        Log.d("경로1:",filePath)
        val intent = Intent("kr.co.yeaeun.ACTION_PLAY")
        Log.d("시작4:", intent.toString())
        // aidl 패키지명
        intent.setPackage("kr.co.yeaeun.test_aidl")
        intent.putExtra("filePath", filePath)
        Log.d("시작5:", filePath)
        bindService(intent, connection, BIND_AUTO_CREATE)
        Log.d("시작6:", filePath)
    }

    var connection: ServiceConnection = object : ServiceConnection { // onbind로 bind 객체가 넘어왔을 때
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            pService = IPlayService.Stub.asInterface(service)
            Log.d("시작3:","y")
            playBtn!!.isEnabled = true
        }

        override fun onServiceDisconnected(name: ComponentName) { // 바인딩이 끊어졌을 때
            pService = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection) // 바인드 끊기
        runThread = false // 쓰레드 종료
    }

    override fun onClick(v: View) {
        if (v === playBtn) {
            if (pService != null) {
                try {

                    pService!!.start()
                    progressBar!!.max = pService!!.getMaxDuration()
                    runThread = true
                    Log.d("시작3:",progressBar.max.toString())
                    val thread = ProgressThread()
                    thread.start()
                    playBtn!!.isEnabled = false
                    stopBtn!!.isEnabled = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else if (v === stopBtn) {
            if (pService != null) {
                try {
                    pService!!.stop()
                    runThread = false
                    progressBar!!.progress = 0
                    playBtn!!.isEnabled = true
                    stopBtn!!.isEnabled = false
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

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

}


