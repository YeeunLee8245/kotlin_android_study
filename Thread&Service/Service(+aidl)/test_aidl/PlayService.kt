package kr.co.yeaeun.test_aidl

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.RemoteException
import android.util.Log


class PlayService : Service() {
    val MEDIA_STATUS_STOP = 0
    val MEDIA_STATUS_RUNNING = 1
    val MEDIA_STATUS_COMPLETED = 2
    var status = MEDIA_STATUS_STOP
    var player: MediaPlayer? = null
    var filePath: String? = null

    override fun onDestroy() {
        player!!.release()
        super.onDestroy()
    }

//    override fun onBind(intent: Intent?): IBinder? {
//        TODO("Not yet implemented")
//    }
    override fun onBind(intent: Intent): IBinder {
        Log.d("시작7:", "추출")
        filePath = intent!!.getStringExtra("filePath")
        Log.d("시작:", "추출")
        return object : IPlayService.Stub() {
            override fun currentPosition(): Int { // 외부앱과 aidl 연동 함수
                return if (player!!.isPlaying) { // 플레이 도중이라면 위치 넘김
                    player!!.currentPosition
                } else {
                    0
                }
            }

            override fun getMaxDuration(): Int {
                return if (player!!.isPlaying) {
                    player!!.duration
                } else {
                    0
                }
            }

            override fun start() {
                player = MediaPlayer()
                try {
                    Log.d("시작서버1:",player.toString()+filePath)
                    player!!.setDataSource(filePath)
                    Log.d("시작서버2:",filePath as String)
                    player!!.prepare()
                    player!!.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                player!!.setOnCompletionListener {
                    status = MEDIA_STATUS_COMPLETED
                }
                status = MEDIA_STATUS_RUNNING
            }

            override fun stop() {
                if (player!!.isPlaying) {
                    player!!.stop()
                }
                status = MEDIA_STATUS_STOP
            }

            override fun getMediaStatus(): Int { // 외부 앱에의한 요청
                return status
            }
        }
    }

//    companion object {
//        const val MEDIA_STATUS_STOP = 0
//        const val MEDIA_STATUS_RUNNING = 1
//        const val MEDIA_STATUS_COMPLETED = 2
//    }
}