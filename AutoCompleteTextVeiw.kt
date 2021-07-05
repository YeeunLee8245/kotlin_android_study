package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    val data1 = arrayOf("데이터1", "데이터2", "데이터3", "데이터4", "데이터5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 2번 인자: autoCompleteTextView에서 사용하는 안드로이드 기본 제공 layout
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, data1)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        // autoCompleteTextView의 adapter라는 프로퍼티가 setter가 지원되지 않고 getter만 지원되기 때문에
        // autoCompleteTextView.adapter = adapter1
        // 형태로 쓸 수 없다. 따라서 set 메소드를 직접 이용해주어야한다(내부적인 이유인듯)
        autoCompleteTextView.setAdapter(adapter1)
        autoCompleteTextView.setOnItemClickListener(listener1)
        // 고차함수로도 가능(메서드가 하나 밖에 없어서 주로 고차함수를 쓴다.
//        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
//            val textView = findViewById<TextView>(R.id.textView)
//            textView.text = "${data1[position]} 항목을 선택했습니다."
//        }
    }

    val listener1 = object : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // 어째서인지 parent에 클릭에 해당하는 autoCompleteTextView가 들어오지 않아서
            // autoCompleteTextView id로는 분기할 수 없다. 이 메서드를 쓸 때는 parent 인자로 when을 쓸 수 없을 것 같다.
//            when(parent?.id){
//                R.id.autoCompleteTextView -> {
//                    val textView = findViewById<TextView>(R.id.textView)
//                    textView.text = "${data1[position]} 항목을 선택했습니다."
//                }
//            }
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = "${data1[position]} 항목을 선택했습니다."
        }

    }
}


