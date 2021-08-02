package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // fragment 객체 생성
            val inputFragment = FirstFragment()
            // FragmentManager 셋팅, Dialog 구분을 위한 문자열
            inputFragment.show(supportFragmentManager, "tag")
        }
    }

}
