package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val imageView3: ImageView = findViewById(R.id.imageView3)
        //imageView3.setImageResource(R.drawable.cat) //이미지 파일로 설정

        val textView1:TextView = findViewById(R.id.textView1)
        //val textView2:TextView = findViewById(R.id.textView2)

        val toggleButton1:ToggleButton = findViewById(R.id.toggleButton1)
        val toggleButton2:ToggleButton = findViewById(R.id.toggleButton2)

        val button1:Button = findViewById(R.id.button1)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)
        val button4:Button = findViewById(R.id.button4)

        button1.setOnClickListener{
            if(toggleButton1.isChecked == true){
                textView1.text = "ON 상태 입니다."
            } else{
                textView1.text = "OFF 상태 입니다."
            }
        }
        button2.setOnClickListener{
            toggleButton1.isChecked = true
        }
        button3.setOnClickListener{
            toggleButton1.isChecked = false
        }
        button4.setOnClickListener {
            toggleButton1.toggle()
        }

        toggleButton1.setOnClickListener(lister1)
        toggleButton2.setOnClickListener {
            val textView2:TextView = findViewById(R.id.textView2)
            if(toggleButton1.isChecked == true) {
                textView2.text = "ON 상태로 설정 되었습니다."
            }else{
                textView2.text = "OFF 상태로 설정 되었습니다."
            }
        }
      }
    val lister1 = object : View.OnClickListener{// View.OnClickListener는 인터페이스이다.
        override fun onClick(v: View?) {
            val toggleButton1:ToggleButton = findViewById(R.id.toggleButton1)   //이 익명클래스에서는 함수 안에 쓸 변수를 선언한다!!
            val textView1:TextView = findViewById(R.id.textView1)
            if(toggleButton1.isChecked == true) {
                textView1.text = "ON 상태로 설정 되었습니다."
            }else{
                textView1.text = "OFF 상태로 설정 되었습니다."
            }
        }
    }

}
