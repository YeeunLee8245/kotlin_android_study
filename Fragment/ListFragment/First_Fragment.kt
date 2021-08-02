package kr.co.yeaeun.viewbasic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment: ListFragment() {

    val data1 = arrayOf("항목1", "항목2", "항목3", "항목4", "항목5")
    // (*중요) 프래그먼트를 통해 보여줄 View를 생성하기 위해 호출
    // 여기에서 만든 View를 Fragment의 View로 사용한다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 넘어온 LayoutInflater로 View 만들기
        val view = inflater.inflate(R.layout.fragment_first, null)

        // listView에 셋팅할 어댑터 생성
        listAdapter = ArrayAdapter<String>(activity as MainActivity, android.R.layout.simple_list_item_1, data1)

       return view
    }

    // 리스트뷰의 항목을 터치했을 때 동작하는 메소드가 제공됨
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        sub_text1.text = data1[position]
    }

}
