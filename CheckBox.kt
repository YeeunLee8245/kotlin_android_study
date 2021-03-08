package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView:TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val checkBox:CheckBox = findViewById(R.id.checkBox)
        val checkBox2:CheckBox = findViewById(R.id.checkBox2)
        val checkBox3:CheckBox = findViewById(R.id.checkBox3)
        button.setOnClickListener{
            val textView:TextView = findViewById(R.id.textView)
            val checkBox:CheckBox = findViewById(R.id.checkBox)
            val checkBox2:CheckBox = findViewById(R.id.checkBox2)
            val checkBox3:CheckBox = findViewById(R.id.checkBox3)
            textView.text = ""
            if (checkBox.isChecked == true){
                textView.append("첫 번째 체크 박스가  체크되어 있습니다.\n")
            }else{
                textView.append("첫 번째 체크 박스가  해제되어 있습니다.\n")
            }
            if (checkBox2.isChecked == true){
                textView.append("두 번째 체크 박스가  체크되어 있습니다.\n")
            }else{
                textView.append("두 번째 체크 박스가  해제되어 있습니다.\n")
            }
            if (checkBox3.isChecked == true){
                textView.append("세 번째 체크 박스가  체크되어 있습니다.\n")
            }else{
                textView.append("세 번째 체크 박스가  해제되어 있습니다.\n")
            }
        }
        button2.setOnClickListener{
            checkBox.isChecked = true
            checkBox2.isChecked = true
            checkBox3.isChecked = true
        }
        button3.setOnClickListener{
            checkBox.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
        }
        button4.setOnClickListener{
            checkBox.toggle()
            checkBox2.toggle()
            checkBox3.toggle()
        }
        checkBox.setOnCheckedChangeListener(listener1)
        checkBox2.setOnCheckedChangeListener(listener1)
        checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true){
                textView.text = "세 번째 체크박스가 체크되었습니다."
            }else{
                textView.text = "세 번째 체크박스가 해제되었습니다."
            }
        }
    }
    val listener1 = object: CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            //첫 번째 인자: 상태가 바뀐 buttonView, 두 번째 인자: buttonView의 상태
            val textView: TextView = findViewById(R.id.textView)
            when(buttonView?.id){
                R.id.checkBox -> {
                    if(isChecked == true) {
                        textView.text = "첫 번째 체크박스가 체크되었습니다."
                    }else{
                        textView.text = "첫 번째 체크박스가 해제되었습니다."
                    }
                }
                R.id.checkBox2 -> {
                    if(isChecked == true) {
                        textView.text = "두 번째 체크박스가 체크되었습니다."
                    }else{
                        textView.text = "두 번째 체크박스가 해제되었습니다."
                    }
                }
            }
        }
    }

}
