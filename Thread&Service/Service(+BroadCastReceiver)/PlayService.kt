package kr.co.yeaeun.viewbasic

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener // 음악 재생과 관련한 라이브러리
import android.os.IBinder


class PlayService : Service(), OnCompletionListener {
    var player: MediaPlayer? = null
    var filePath: String? = null

    // Activity가 실행시키는 브로드캐스트 리시버
    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val mode = intent.getStringExtra("mode")
            if (mode != null) {
                if (mode == "start") {
                    try {
                        if (player != null && player!!.isPlaying) {
                            player!!.stop()
                            player!!.release()
                            player = null
                        }
                        // 음악 play를 위한 MediaPlayer 준비
                        player = MediaPlayer()
                        player!!.setDataSource(filePath)
                        player!!.prepare()
                        player!!.start()
                        // Activity에 데이터(duration) 전달
                        val aIntent = Intent("com.example.PLAY_TO_ACTIVITY")
                        aIntent.putExtra("mode", "start")
                        aIntent.putExtra("duration", player!!.duration)
                        sendBroadcast(aIntent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else if (mode == "stop") {
                    if (player != null && player!!.isPlaying) {
                        player!!.stop()
                        player!!.release()
                        player = null
                    }
                }
            }
        }
    }

    override fun onCompletion(mp: MediaPlayer) { // 음악 플레이가 끝나면 activity에 알려준다
        val intent = Intent("com.example.PLAY_TO_ACTIVITY")
        intent.putExtra("mode", "stop")
        sendBroadcast(intent)
        // Service 자신을 종료 시킨다
        stopSelf()
    }

    override fun onCreate() {
        super.onCreate()
        registerReceiver(receiver, IntentFilter("com.example.PLAY_TO_SERVICE"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    // 최초 서비스 구동 또는 구동 되고있는 서비스 재구동(음악이 플레이 되고 있는 상황)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        filePath = intent.getStringExtra("filePath")
        if (player != null) {
            val aIntent = Intent("com.example.PLAY_TO_ACTIVITY")
            aIntent.putExtra("mode", "restart")
            aIntent.putExtra("duration", player!!.duration)
            aIntent.putExtra("current", player!!.currentPosition)
            sendBroadcast(aIntent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }
}