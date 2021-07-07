package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // XML로 메뉴를 구성한다./ R은 res 폴더를 의미
        // menuInflater.inflate(R.menu.main_menu, menu)
        //.?: null 안전성 보장
        // 그룹 아이디: 그룹 사용X, 메뉴 아이디, 메뉴 순서, 메뉴 항목 이름
        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴1")
        //menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴2")

        // 서브 메뉴
        val sub = menu?.addSubMenu("코드 메뉴2")
        sub?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴2-1")
        sub?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴2-2")

        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴3")

        // true를 반환해야 화면에 나타난다.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 메뉴를 id 별로 분기
//        when (item.itemId){
//            R.id.item1 -> {
//                textView.text = "메뉴1를 눌렀습니다."
//            }
////            R.id.item2 -> {
////                textView.text = "메뉴2를 눌렀습니다."
////            }
//            R.id.item2_1 -> {
//                textView.text = "메뉴2-1를 눌렀습니다."
//            }
//            R.id.item2_2 -> {
//                textView.text = "메뉴2-2를 눌렀습니다."
//            }
//            R.id.item3 -> {
//                textView.text = "메뉴3를 눌렀습니다."
//            }
//        }

        when(item.itemId){
            Menu.FIRST -> {
                textView.text = "코드 메뉴1을 눌렀습니다."
            }
            Menu.FIRST +10 -> {
                textView.text = "코드 메뉴2-1을 눌렀습니다."
            }
            Menu.FIRST + 20 -> {
                textView.text = "코드 메뉴2-2을 눌렀습니다."
            }
            Menu.FIRST + 2 -> {
                textView.text = "코드 메뉴3을 눌렀습니다."
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
