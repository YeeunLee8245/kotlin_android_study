package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fragment 객체 생성
        val inputFragment = FirstFragment()
        val tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.container1,inputFragment)
        tran.commit()
    }

}
