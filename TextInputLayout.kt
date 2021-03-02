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
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView1: TextView = findViewById(R.id.textView1)
        val textInputLayout1: TextInputLayout = findViewById(R.id.textInputLayout1)
        val button1:Button = findViewById(R.id.button1)
        button1.setOnClickListener{
            val textInputLayout1: TextInputLayout = findViewById(R.id.textInputLayout1)
            textView1.text = textInputLayout1.editText?.text
            textInputLayout1.editText?.clearFocus()     // 포커스 아웃
            //키보드 내리기
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(textInputLayout1.editText?.windowToken,0)
        }
        textInputLayout1.editText?.addTextChangedListener(listner1)
    }

    val listner1 = object : TextWatcher{    // 이 object는 함수 3개가 명시되어 있어야함(함수를 전부 쓰지 않더라도)
        override fun afterTextChanged(s: Editable?) {
            val textInputLayout1: TextInputLayout = findViewById(R.id.textInputLayout1)
            if(s != null){
                if(s.length > 10){
                    textInputLayout1.error = "10글자 이하로 입력해주세요"
                }else{
                    textInputLayout1.error = null
                }
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
    }
}
