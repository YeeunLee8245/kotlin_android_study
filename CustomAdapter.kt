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

        val list1: ListView = findViewById(R.id.list1)
        list1.adapter = adapter1
    }

    val adapter1 = object: BaseAdapter(){
        override fun getCount(): Int {
            // 항목의 개수를 반환한다.
            return data1.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        // converView는 처음에 null이 들어가고 화면에 나타나는 항목 View가 변동됨에 따라
        // 재사용되는 View를 저장하고 있다가 해당 View가 호출될 때 converView에 해당 View를 삽입한다.
        // 따라서 또 다시 객체를 만들 필요X => 메모리 절약
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            // 재사용 가능한 View를 변수에 담는다.(convertView가 readOnly로 세팅되어져있기 때문에 var변수 따로 지정
            var rowView = convertView

            if(rowView == null){
                // 각 항목에 XML에 정의된 Resource(row activity)를 View 객체로 변수에 반환한다.
                // 첫번째 인자를 두번째 인자에 붙이는 형식, 아무것도 없는 레이어에 붙일 것이기 때문에 두 번째 인자에 null
               rowView = layoutInflater.inflate(R.layout.row, null)
            }
            // row에 들어있는 view들은 MainActivity가 관리하는게X 따라서 객체 주소값을 직접 받아야함(근데 요즘 코틀린에서도 MainActivity의 객체도 직접 받아서 별 차이X)
            // 항목 뷰에 내부에 배치되어 있는 뷰들의 주소값을 가져온다.
            val text1 = rowView?.findViewById<TextView>(R.id.rowTextView)
            val btn1 = rowView?.findViewById<Button>(R.id.rowButton1)
            val btn2 = rowView?.findViewById<Button>(R.id.rowButton2)

            // ?. => null이 아닐 때만 수행
            text1?.text = data1[position]
            btn1?.tag = position
            btn2?.tag = position
            val textView = findViewById<TextView>(R.id.textView)
            btn1?.setOnClickListener{
                // it으로 btn1 View 객체가 들어옴
                textView.text = "첫 번째 버튼을 눌렀습니다 : ${it.tag}"
            }
            btn2?.setOnClickListener{
                // it으로 btn2 View 객체가 들어옴
                textView.text = "두 번째 버튼을 눌렀습니다 : ${it.tag}"
            }
//            rowView?.run{
//                rowTextView.text  = "~"
//            }

            return rowView!! // 반환타입은 null 허용X지만, 인자로는 null허용 타입(View?)을 넣어주었기 때문에 !!(null 들어가면 안됨 명시)
        }
    }


}
