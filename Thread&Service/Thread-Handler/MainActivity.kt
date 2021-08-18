package kr.co.yeaeun.viewbasic

import android.content.res.Configuration
import android.os.*
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
class MainActivity : AppCompatActivity() {
    var loopFlag = true
    var isFirst = true
    var isRun: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = object : Handler(Looper.myLooper()!!){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                when(msg.what){
                    1 -> {
                        textView.setText("${msg.arg1}")
                    }
                    2 -> {
                        textView.setText("${msg.obj}")
                    }
                }
            }
        }

        startView.setOnClickListener{
            if(isFirst){
                isFirst = false
                isRun = true
                thread {
                    var count = 10
                    while(loopFlag){
                        SystemClock.sleep(1000)
                        if(isRun){
                            count -= 1
                            var msg = Message()
                            msg.what = 1
                            msg.arg1 = count
                            handler.sendMessage(msg)
                            if (count == 0){
                                msg = Message()
                                msg.what = 2
                                msg.obj = "Finish!!"
                                handler.sendMessage(msg)
                                loopFlag = false
                            }
                        }
                    }
                }
            }else{
                isRun = true
            }
        }
        pauseView.setOnClickListener{
            isRun = false
        }


    }


}