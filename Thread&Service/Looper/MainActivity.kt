package kr.co.yeaeun.viewbasic

import android.content.res.Configuration
import android.os.*
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var oneThread: OneThread
    val oddDatas:ArrayList<String> = ArrayList()
    val evenDatas:ArrayList<String> = ArrayList()
    lateinit var oddAdapter:ArrayAdapter<String>
    lateinit var evenAdapter:ArrayAdapter<String>
    lateinit var handler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        oddAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, oddDatas)
        evenAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, evenDatas)

        oddListView.adapter = oddAdapter
        evenListView.adapter = evenAdapter

        handler = Handler(Looper.myLooper()!!)

        oneThread = OneThread()
        oneThread.start()

        val twoThread = TwoThread()
        twoThread.start()
    }

    inner class OneThread: Thread(){
        lateinit var oneHandler:Handler

        override fun run() {
            super.run()
            Looper.prepare()
            oneHandler = object : Handler(Looper.myLooper()!!){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    SystemClock.sleep(1000)
                    val data: Int = msg.arg1
                    if (msg.what == 0) {
                        handler.post {
                            evenDatas.add("even:" + data)
                            evenAdapter.notifyDataSetChanged()
                        }
                    } else if (msg.what == 1) {
                        handler.post {
                            oddDatas.add("odd:" + data)
                            oddAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            Looper.loop()
        }
    }

    inner class TwoThread: Thread(){
        override fun run() {
            super.run()
            val random = java.util.Random()
            for(i in 1..10){
                SystemClock.sleep(1000)
                var data = random.nextInt(10) // 0~10 미만의 정수형 난수
                val message = Message()
                if(data%2 == 0){
                    message.what = 0
                }else{
                    message.what = 1
                }
                message.arg1 = data
                message.arg2 = i
                oneThread.oneHandler.sendMessage(message)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        oneThread.oneHandler.looper.quit()
    }


}