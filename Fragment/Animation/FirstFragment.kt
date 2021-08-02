package kr.co.yeaeun.viewbasic

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        first_button1.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.setFragment("second")
        }
    }
}
