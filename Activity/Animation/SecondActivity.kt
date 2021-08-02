package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        button2.setOnClickListener {
            finishActivity()
        }
    }

    // backbutton을 눌렀을 때 호출되는 메서드드
   override fun onBackPressed() {
        // 부모 클래스의 메소드를 호출해서 Activity를 종료시키기 때문에 삭제하자
        //super.onBackPressed()
    }

    fun finishActivity(){
        finish()
        //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        overridePendingTransition(R.anim.slide_xml3,R.anim.slide_xml4)
    }
}
