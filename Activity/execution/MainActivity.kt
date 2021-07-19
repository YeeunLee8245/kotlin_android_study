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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        // MainActivity 위에 SecondActivity 올리기(뒤로가기(종료) 버튼 누르면 MainActivity)
        button.setOnClickListener{
            // intent 객체 생성 (context, 지정할 Activity, java 파일::자바가 컴파일된 클래스 파일 *reflection)
            val second_intent = Intent(this, SecondActivity::class.java) // 마지막 인자: SecondActivity 클래스 지정

            // 안드로이드 OS에게 SecondActivity 전달
            startActivity(second_intent)
        }
    }


}
