package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fragment 객체 생성
        val frag1 = FirstFragment()

        button.setOnClickListener {
            // Fragment 작업 시작
             val tran = supportFragmentManager.beginTransaction()
            // Fragment를 셋팅한다.
            tran.add(R.id.container1,frag1) // 위치(레이아웃)을 지정해줘야함
            //tran.replace(R.id.container1,frag1) // 대체한다.

            // 백버튼 클릭시 Activity 종료X, 해당 Fragment 종료
            tran.addToBackStack(null) // backstack에 삽입(1번 인자: stack 안에서 구별하기 위한 이름)
            tran.commit()
        }

        button2.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.remove(frag1)
            tran.commit()
        }

    }

}
