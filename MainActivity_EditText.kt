package kr.co.yeaeun.viewbasic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button1)
        val editText1: EditText = findViewById(R.id.editText1)

        button1.setOnClickListener {
            val textView1: TextView = findViewById(R.id.textView1)
            val editText1: EditText = findViewById(R.id.editText1)
            textView1.text = editText1.text
            // 입력이 끝나면 키보드 내려가기, 포커스 사라지기
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager // 안드로이드 os에서 운용되는 기능들을 가져다 씀
            imm.hideSoftInputFromWindow(editText1.windowToken, 0)

            editText1.clearFocus()
        }
        editText1.addTextChangedListener(listener1)// 같은 이름의 고차함수가 존재하지만 다른 얘기임
        editText1.setOnEditorActionListener { v, actionId, event -> // 이 고차함수는 람다를 설정할 때 반환값을 반드시 설정해주어야함 여기서는 false로 설정(true: 키보드 안 내려감, false: 키보드 내려감)
            val textView1: TextView = findViewById(R.id.textView1)
            val textView2: TextView = findViewById(R.id.textView2)
            textView1.text = "엔터 버튼을 눌렀습니다."
            textView2.text = editText1.text
            true
        }
    }

    val listener1 = object : TextWatcher {
        // 문자열이 변경되기 전
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { // s: 입력하기 전 문자열
            val textView1: TextView = findViewById(R.id.textView1)
            textView1.text = "before : $s"
        }

        // 문자열 변경 작업이 완료되었을 때
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { // s: 입력이 끝난 후 문자열(새롭게 바뀐 문자열)
            val textView2: TextView = findViewById(R.id.textView2)
            textView2.text = "changed : $s"
        }

        // 변경된 문자열이 화면에 반영되었을 때 < 주로 사용!!
        override fun afterTextChanged(s: Editable?) { // s: 완전히 반영된 문자열
            val textView3: TextView = findViewById(R.id.textView3)
            textView3.text = "after : $s"
        }
    }
}
