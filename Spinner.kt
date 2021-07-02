package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    val data1 = arrayOf("데이터1", "데이터2", "데이터3", "데이터4", "데이터5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 어댑터를 생성한다. 접혔을 때의 모습을 구성할 Layout을 설정한다.
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)
        // 펼쳐졌을 때의 모습을 구성하기 위한 Layout을 지정한다.
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // 어댑터를 스피너에 설정
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter1

        // spinner은 리스너를 설정할 때 메서드가 아니라 프로퍼티로 설정한다.(특이점)
        spinner.onItemSelectedListener = listener1
    }

    val listener1 = object : AdapterView.OnItemSelectedListener{
        // parent: 설정한 spinner 객체, position: 인덱스 번호
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(parent?.id){
                R.id.spinner ->{
                    val textView = findViewById<TextView>(R.id.textView)
                    textView.text = "${data1[position]} 번째 항목이 선택되었습니다."
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }




}
