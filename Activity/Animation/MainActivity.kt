package kr.co.yeaeun.viewbasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // fragment 객체 생성
    val firstFragment = FirstFragment()
    val secondFragment = SecondFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in)
            overridePendingTransition(R.anim.slide_xml1,R.anim.slide_xml2)
        }
    }


}
