package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val SECOND_ACTIVITY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        // MainActivity 위에 SecondActivity 올리기(뒤로가기(종료) 버튼 누르면 MainActivity)
        button.setOnClickListener{
            // intent 객체 생성 (context, 지정할 Activity, java 파일::자바가 컴파일된 클래스 파일 *reflection)
            val second_intent = Intent(this, SecondActivity::class.java) // 마지막 인자: SecondActivity 클래스 지정

            val t1 = TestClass()
            t1.data1 = 100
            t1.data2 = "문자열1"

            // parcelable로 구현한 객체 세팅(writeToParcel 메소드 자동 호출)
            // parcel이 intent에 담기게 됨
           second_intent.putExtra("obj1",t1)

            // 안드로이드 OS에게 SecondActivity 전달
            //startActivity(second_intent)

            startActivityForResult(second_intent,SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SECOND_ACTIVITY){
            if(resultCode == RESULT_OK){
                // 객체를 추출한다.
                val obj2 = data?.getParcelableExtra<TestClass>("obj2")

                textView.text = "obj2.data1: ${obj2?.data1}\n"
                textView.append("obj2.data2: ${obj2?.data2}\n")
            }
        }
    }

}
