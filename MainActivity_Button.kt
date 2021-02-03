package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 버튼에 리스너를 설정한다. -2
        val button1:Button = findViewById(R.id.button1)
        button1.setOnClickListener(listener1)
        val imageButton1:ImageButton = findViewById(R.id.imageButton1)
        imageButton1.setOnClickListener(listener2)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)
        button2.setOnClickListener(listener3)
        button3.setOnClickListener(listener3)
        //onclicklistener함수를 고차함수로도 작성 가능< 세팅한 람다함수로 돌아감
        val button4:Button = findViewById(R.id.button4)
        button4.setOnClickListener{
            val text1: TextView = findViewById(R.id.text1)
            text1.text = "다섯 번째 버튼을 눌렀습니다."
        }
    }
    // val text1: TextView = findViewById(R.id.text1) 하면 안된다 이유는?
    // 버튼을 클릭하면 동작하는 리스너 객체-1
    val listener1 = object: View.OnClickListener{ // 익명 중첩 클래스
        override fun onClick(v: View?) {
            val text1: TextView = findViewById(R.id.text1)
            text1.text = "첫 번째 버튼을 눌렀습니다."
        }
    }

    val listener2 = object: View.OnClickListener{
        override fun onClick(v: View?) {
            val text1: TextView = findViewById(R.id.text1)
            text1.text = "두 번째 버튼을 눌렀습니다"
        }
    }

    val listener3 = object: View.OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.button2 ->{
                    val text1: TextView = findViewById(R.id.text1)
                    text1.text = "세 번째 버튼을 눌렀습니다."
                }
                R.id.button3 ->{
                    val text1: TextView = findViewById(R.id.text1)
                    text1.text = "네 번째 버튼을 눌렀습니다."
                }
            }
        }
    }
}