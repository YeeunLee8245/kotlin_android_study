package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val data1 = arrayOf(
            "문자열1", "문자열2", "문자열3", "문자열4", "문자열5",
            "문자열6", "문자열7", "문자열8", "문자열9", "문자열10",
            "문자열11", "문자열12", "문자열13", "문자열14", "문자열15",
            "문자열16", "문자열17", "문자열18", "문자열19", "문자열20",
            "문자열21", "문자열22", "문자열23", "문자열24", "문자열25",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list1:ListView = findViewById(R.id.list1)
        //1번째 인자:
        // context: 작업을 하기 위해서 필요한 정보가 담겨져있는 것들 ex. 화면을 구성하기 위해서 액정의 사이즈 등
        // this: activity(MainActivity class 자체), 화면에 관련된 다양한 정보가 담겨져있다(화면 관련 말고도 더 있는듯?)
        // 2번째 인자: 항목 하나를 구성하기 위해 사용할 Layout 파일, android OS에서 list view 항목으로 사용하라고 제공하고 있는 것(ctrl 누르고 클릭시 코드 확인 가능)
        // 3번째 인자: 리스트의 항목(배열)
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data1)
        list1.adapter = adapter1

        list1.setOnItemClickListener(listener1)
    }

    val listener1 = object : AdapterView.OnItemClickListener{
        // 1번째 인자: 이벤트가 발생한 항목을 가지고 있는 AdapterView
        // 2번째 인자: 이벤트가 발생한 항목 View
        // 3번째 인자: 이벤트가 발생한 항목의 index 번호(정수, 0부터)
        // AdapterView id가 한 화면에 하나 존재하더라도 when으로 분기해서 처리하자(가독성 때문)
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val textView:TextView = findViewById(R.id.textView)
            when(parent?.id){
                R.id.list1 -> {
                    textView.text = "$position 번째 항목을 클릭했습니다.\n"
                    textView.append("${data1[position]}를 터치했습니다.")
                }
            }
        }
    }


}
