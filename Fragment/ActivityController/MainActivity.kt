package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // fragment 객체 생성
    val inputFragment = FirstFragment()
    val resultFragment = SecondFragment()

    // 프래그먼트들이 사용할 변수
    var value1 = ""
    var value2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment("input")
    }

    fun setFragment(name: String){
        val tran = supportFragmentManager.beginTransaction()
        when(name){
            "input" -> {
                tran.replace(R.id.container1,inputFragment)
            }
            "result" -> {
                tran.replace(R.id.container1,resultFragment)
                tran.addToBackStack(null) // 뒤로가기 누르면 input으로 돌아감
            }
        }
        tran.commit()
    }
}
