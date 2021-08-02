package kr.co.yeaeun.viewbasic

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

        setFragment("first")
    }

    fun setFragment(name: String){
        val tran = supportFragmentManager.beginTransaction()

        when(name){
            "first" -> {
                tran.replace(R.id.container1, firstFragment)
            }
            "second" -> {
                // Activity 나타날 때
                //tran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                // tran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                // Activity 나타나고 사라질 때
                //tran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

                //tran.setCustomAnimations(R.anim.fade_xml1, R.anim.fade_xml2, R.anim.fade_xml1, R.anim.fade_xml2)

                tran.setCustomAnimations(R.anim.slide_xml1,R.anim.slide_xml2,R.anim.slide_xml3,R.anim.slide_xml4)
                tran.replace(R.id.container1, secondFragment)
                tran.addToBackStack(null)
            }
        }
        tran.commit()
    }

}
