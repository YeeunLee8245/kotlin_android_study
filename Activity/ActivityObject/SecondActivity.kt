package kr.co.yeaeun.viewbasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // parcel 데이터 추출
        // CREATOR의 createFromParcel 메소드 호출
       val obj1 = intent.getParcelableExtra<TestClass>("obj1") // MainActivity에서 객체를 저장할 때 사용한 이름

        textView2.text = "obj1.data1: ${obj1?.data1}\n"
        textView2.append("obj1.data2: ${obj1?.data2}\n")
        // import해서 button2 객체를 가져올 수도
        button2.setOnClickListener{

            val t2 = TestClass()
            t2.data1 = 200
            t2.data2 = "문자열2"

            val result_intent = Intent()
            result_intent.putExtra("obj2",t2)

            setResult(RESULT_OK,result_intent)
            // 백 버튼과 같은 용도
            finish()
        }

    }
}
