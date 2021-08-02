package kr.co.yeaeun.viewbasic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment: Fragment() {

    // (*중요) 프래그먼트를 통해 보여줄 View를 생성하기 위해 호출
    // 여기에서 만든 View를 Fragment의 View로 사용한다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 넘어온 LayoutInflater로 View 만들기
        val view = inflater.inflate(R.layout.fragment_second, null)
        return view
    }

}
