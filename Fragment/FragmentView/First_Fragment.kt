package kr.co.yeaeun.viewbasic

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment: Fragment() {

    // (*중요) 프래그먼트를 통해 보여줄 View를 생성하기 위해 호출
    // 여기에서 만든 View를 Fragment의 View로 사용한다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 넘어온 LayoutInflater로 View 만들기
        val view = inflater.inflate(R.layout.fragment_first, null)

        // 내부의 View들의 주소값을 가져온다.
        // onCreateView 메소드 안에서는 frag_text1, frag_btn1가 아직 만들어지지 않았기 때문에 이름으로만 가져올 수 X
//        val text1 = view.findViewById<TextView>(R.id.frag_text1)
//        val btn1 = view.findViewById<TextView>(R.id.frag_btn1)
//
//        btn1.setOnClickListener{
//            text1.text = "Fragment 문자열"
//        }

        // 여기서 view를 반환하면 view 객체 이름들을 변수로 사용 가능
        return view
    }

    // (*중요) 프래그먼트를 통해 보여줄 View가 생성되면 호출(onCreateView의 작업이 끝나면 호출)
    // Fragment가 관리하는 view 내부의 View의 주소값들이 셋팅된 후이기 때문에 view 객체 이름들을 변수로 사용 가능
    // 따라서 Fragment의 View를 다루려면 onViewCreated 메소드 안에서 다루길 권장
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_btn1.setOnClickListener {
            frag_text1.text = "Fragment 문자열"
        }

        frag_btn2.setOnClickListener {
            // MainActivity를 추출한다.
            // 형변환 잊지말기
            val parent = activity as MainActivity
            parent.activity_text1.text = "Activity 문자열"
        }

    }


}
