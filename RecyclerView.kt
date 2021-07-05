package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    val imgRes = arrayOf(
            R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3, R.drawable.imgflag4, R.drawable.imgflag5, R.drawable.imgflag6,
            R.drawable.imgflag7, R.drawable.imgflag8,
    )

    val data1 = arrayOf("토고", "프랑스 문자열을 길게 작성합니다.", "스위스", "스페인",
            "일본 문자열을 길게 작성합니다.", "독일", "브라질", "대한민국")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler1 = findViewById<RecyclerView>(R.id.recycler1)

        val adapter1 = RecyclerAdapter()
        recycler1.adapter = adapter1

        //recycler1.layoutManager = LinearLayoutManager(this)
        //recycler1.layoutManager = GridLayoutManager(this, 2)
        recycler1.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //recycler1.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
    }

    // RecyclerView의 Adapter 클래스
    // override 메소드의 자리 순서까지 지켜줘야 에러가 안 난다
    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        // 항목 구성을 위해 사용할 ViewHolder 객체가 필요할 때 호출되는 메서드
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // 항목으로 사용할 View 객체를 생성한다.
            val itemView = layoutInflater.inflate(R.layout.row, null)
            val holder = ViewHolderClass(itemView)

            return holder
        }

        // RecyclerView.ViewHolder.ItemView(각 항목 레이어)를 통해 항목을 구성할 때 항목 내의 View 객체에 데이터를 셋팅한다.
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val rowImageView = holder.itemView.findViewById<ImageView>(R.id.rowImageView)
            val rowTextView = holder.itemView.findViewById<TextView>(R.id.rowTextView)
            rowImageView.setImageResource(imgRes[position])
            rowTextView.text = data1[position]
        }

        override fun getItemCount(): Int {
            return imgRes.size
        }

        // ViewHolder 클래스: inner클래스로 생성해준다. 왜냐하면 외부 클래스 지역 클래스를 사용해야하기 때문
        inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView){
            // 항목 View 내부의 View 객체의 주소값을 담는다.
            val rowImageView = itemView.findViewById<ImageView>(R.id.rowImageView)
            val rowTextView = itemView.findViewById<TextView>(R.id.rowTextView)

        }
    }
}


