package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
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

    // Activity가 생성될 때 자동으로 호출된다.
    // 화면 전환이 발생할 때 자동으로 호출된다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Yeeun test", "onCreate") // 메시지 태그명, 띄울 메시지
    }

    // onCreate 메서드 호출 이후에 자동으로 호출
    // Activity가 정지 상태(일시 정지X)가 되었다가 활동 상태로 돌아올 때 호출
    override fun onStart() {
        super.onStart()
        Log.d("Yeeun test", "onStart")
    }

    // onStart 메서드가 호출된 이후에 자동으로 호출된다.
    // Activity가 일시 정지 되었다가 다시 돌아올 때 호출
    override fun onResume() {
        super.onResume()
        Log.d("Yeeun test", "onResume")
    }

    // Activity가 정지 상태가 되었다가 활동 상태로 돌아갈 때 onStart 전에 호출된다.
    override fun onRestart() {
        super.onRestart()
        Log.d("Yeeun test", "onRestart")
    }

    // Activity가 일시 정지 상태가 될 때 호출된다.
    // 화면상에서 완전히 사라지거나 현재 화면 위에 작은 팝업 창 같은 것이 나타날 때 호출
    override fun onPause() {
        super.onPause()
        Log.d("Yeeun test", "onPause")
    }

    // Activity가 화면에서 사라질 때 호출된다.
    override fun onStop() {
        super.onStop()
        Log.d("Yeeun test", "onStop")
    }

    // 현재 Activity의 수행이 완전히 종료되어 메모리상에서 제거될 때 호출출
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Yeeun test", "onDestroy")
    }


}
