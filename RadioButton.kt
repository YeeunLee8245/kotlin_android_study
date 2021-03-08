package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)

        button.setOnClickListener{
            // 라디오 버튼 그룹에서 하나만 true이면 되기 때문에 false를 설정해줄 필요 X
            val radioButton3: RadioButton = findViewById(R.id.radioButton3)
            val radioButton6: RadioButton = findViewById(R.id.radioButton6)
            radioButton3.isChecked = true
            radioButton6.isChecked = true
        }
        button2.setOnClickListener {
            val radioGroup1: RadioGroup = findViewById(R.id.radioGroup1)
            val radioGroup2: RadioGroup = findViewById(R.id.radioGroup2)
            val textView: TextView = findViewById(R.id.textView)
            val textView2: TextView = findViewById(R.id.textView2)
            when(radioGroup1.checkedRadioButtonId){
                R.id.radioButton -> {
                    textView.text = "라디오 1-1이 체크되어 있습니다."
                }
                R.id.radioButton2 -> {
                    textView.text = "라디오 1-2이 체크되어 있습니다."
                }
                R.id.radioButton3 -> {
                    textView.text = "라디오 1-3이 체크되어 있습니다."
                }
            }
            when(radioGroup2.checkedRadioButtonId){
                R.id.radioButton4 -> {
                    textView2.text = "라디오 2-1이 체크되어 있습니다."
                }
                R.id.radioButton5 -> {
                    textView2.text = "라디오 2-2이 체크되어 있습니다."
                }
                R.id.radioButton6 -> {
                    textView2.text = "라디오 2-3이 체크되어 있습니다."
                }
            }
        }
        val radioGroup1: RadioGroup = findViewById(R.id.radioGroup1)
        val radioGroup2: RadioGroup = findViewById(R.id.radioGroup2)
        radioGroup1.setOnCheckedChangeListener(listener1)
        radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            val textView:TextView =findViewById(R.id.textView)
            when(checkedId){
                R.id.radioButton4 -> {
                    textView.text = "라디오 2-1이 체크 되었습니다."
                }
                R.id.radioButton5 -> {
                    textView.text = "라디오 2-2이 체크 되었습니다."
                }
                R.id.radioButton6 -> {
                    textView.text = "라디오 2-3이 체크 되었습니다."
                }
            }
        }
    }
    val listener1 = object:RadioGroup.OnCheckedChangeListener{
        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
            // 그룹마다 분기 가능
            val textView:TextView =findViewById(R.id.textView)
            when(group?.id){
                R.id.radioGroup1 ->{
                    when(checkedId){
                        R.id.radioButton -> {
                            textView.text = "라디오 1-1이 체크되었습니다."
                        }
                        R.id.radioButton2 -> {
                            textView.text = "라디오 1-2이 체크되었습니다."
                        }
                        R.id.radioButton3 -> {
                            textView.text = "라디오 1-3이 체크되었습니다."
                        }
                    }
                }
            }
        }
    }

}
