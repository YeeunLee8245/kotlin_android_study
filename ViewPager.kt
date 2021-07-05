package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    val data1 = arrayOf("데이터1", "데이터2", "데이터3", "데이터4", "데이터5")
    val viewList = ArrayList<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager1 = findViewById<ViewPager>(R.id.pager1)

        // xml를 view객체로 만들어주기기, 인자1: xml/ 인자2: 만들어진 view 객체를 배치할 Layout
        val view1 = layoutInflater.inflate(R.layout.view1, null)
        val view2 = layoutInflater.inflate(R.layout.view2, null)
        val view3 = layoutInflater.inflate(R.layout.view3, null)
        val view4 = layoutInflater.inflate(R.layout.view1, null)
        val view5 = layoutInflater.inflate(R.layout.view2, null)
        val view6 = layoutInflater.inflate(R.layout.view3, null)

        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        viewList.add(view4)
        viewList.add(view5)
        viewList.add(view6)

        // view page에 세팅할 어댑터 만들기, object에 마우스 => 클릭으로 메소드 추가 가능
        val adapter = object : PagerAdapter() {
            // ViewPager가 보여줄 View의 개수
            override fun getCount(): Int {
                return viewList.size
            }
            // ViewPager가 보여줄 화면 View를 반환하는 메서드, View 페이지를 구성하는 객체들도 추가적으로 만들어줌
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                pager1.addView(viewList[position]) // viewpage에 세팅
                return viewList[position]
            }
            // instantiateItem이 반환한 객체를 화면으로 사용할 것인가를 검사하는 메서드
            // instantiateItem 리런 값이 `object`로 들어옴(object라는 키워드가 있어서 백틱을 사용해준 것임, 헷갈리니까 바꾸는 것을 권장)
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            // 사라지는 View 객체를 소멸하는 메서드
            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                pager1.removeView(`object` as View)
            }
        }

        pager1.adapter = adapter

        val listener1 = object : ViewPager.OnPageChangeListener{
            // 페이지 스크롤이 변경되었을 때 호출되는 메서드
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            // 페이지 스크롤이 끝났을 때 호출되는 메서드
            override fun onPageSelected(position: Int) {
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = "${position} 번째 View가 나타났습니다."
            }
            // 페이지를 선택했을 때 호출되는 메서드
            override fun onPageScrollStateChanged(state: Int) {
                
            }

        }
        // ViewPager는 리스너 객체를 여러개 추가해서 사용할 수 있기 때문에 리스너 설정 메소드 이름이 add로 시작한다.
        pager1.addOnPageChangeListener(listener1)
    }


}


