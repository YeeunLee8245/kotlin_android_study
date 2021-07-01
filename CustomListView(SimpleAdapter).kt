package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val imgRes = arrayOf(
            R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3, R.drawable.imgflag4, R.drawable.imgflag5, R.drawable.imgflag6,
            R.drawable.imgflag7, R.drawable.imgflag8,
    )

    val data1 = arrayOf("토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국")
    val data2 = arrayOf("togo", "france", "swiss", "spain", "japan", "german", "brazil", "korea")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 하나의 배열 인덱스에 HashMap을 가지고 있는 형태
        val dataList = ArrayList<HashMap<String, Any>>()

        // HashMap에 값 채워넣고 그 HashMap을 배열에 추가하기
        for (i in imgRes.indices){
            val map = HashMap<String, Any>()
            map["img"] = imgRes[i]
            map["data1"] = data1[i]
            map["data2"] = data2[i]

            dataList.add(map)
        }

        // 키 이름과 키 이름에 해당하는 View Id 1대1 대응
        val keys = arrayOf("img", "data1", "data2")
        // SimpleAdapter의 id인자에는 int 배열로 타입이 지정되어있기 때문에 intArrayOf로 배열을 생성해줘야함
        val ids = intArrayOf(R.id.rowImageView, R.id.rowTextView1, R.id.rowTextView2)

        val adapter1 = SimpleAdapter(this, dataList, R.layout.row, keys, ids)
        val list1:ListView = findViewById(R.id.list1)
        list1.adapter = adapter1

        list1.setOnItemClickListener { parent, view, position, id ->
            val textView: TextView = findViewById(R.id.textView)
            textView.text = "${data1[position]}"
        }
    }


}
