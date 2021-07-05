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

        val list1 = findViewById<ListView>(R.id.list1)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,data1)
        list1.adapter = adapter1
        // 모드 변경
        list1.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // 다중 선택이 가능하기 때문에 setItemChecked 메소드도 반복적으로 설정 가능
        list1.setItemChecked(0, true)
        list1.setItemChecked(2, true)
        list1.setItemChecked(4, true)

        button.setOnClickListener {
            textView.text = ""

            // 현재 체크 상태에 관련된 객체를 가져온다.(체크된 것 + 체크 해제된 것)
            val boolArray = list1.checkedItemPositions

            // boolArray는 배열 객체가 아니기 때문에 내부에 있는 size 메소드로 크기를 확인할 수 있다.
            //textView.text = "개수 : ${boolArray.size()}"

            for (idx in 0 until boolArray.size()){
                // keyAt: boolArray에 들어있는 항목의 인덱스 번호를 가져옴
                // textView.append("${boolArray.keyAt(idx)} ")
                val key = boolArray.keyAt(idx)
                // 해당 항목이 체크되어있는지 확인하려면 해당 인덱스의 값이 true인지 확인
                if (boolArray[key] == true){
                    textView.append("${data1[key]}, ")
                }
            }


        }


    }


}


