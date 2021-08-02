package kr.co.yeaeun.viewbasic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment: Fragment() {

    // (*중요) 프래그먼트를 통해 보여줄 View를 생성하기 위해 호출
    // 여기에서 만든 View를 Fragment의 View로 사용한다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 넘어온 LayoutInflater로 View 만들기
        val view = inflater.inflate(R.layout.fragment_first, null)
        return view
    }

    // (*중요) 프래그먼트를 통해 보여줄 View가 생성되면 호출(onCreateView의 작업이 끝나면 호출)
    // Fragment가 관리하는 view 내부의 View의 주소값들이 셋팅된 후이기 때문에 view 객체 이름들을 변수로 사용 가능
    // 따라서 Fragment의 View를 다루려면 onViewCreated 메소드 안에서 다루길 권장
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_buton.setOnClickListener {
            val mainActivity = activity as MainActivity
            // edit.text로하면 string 객체가 아니라 editable로 반환되기 때문에 toString() 사용
            mainActivity.value1 = input_edit1.text.toString()
            mainActivity.value2 = input_edit2.text.toString()
            mainActivity.setFragment("result")
        }

    }

    override fun onResume() {
        super.onResume()
        // 돌아갈 때 edit 초기화화
        input_edit1.setText("")
        input_edit2.setText("")
    }


}
