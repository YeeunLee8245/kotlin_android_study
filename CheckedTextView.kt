package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkedTextView: CheckedTextView = findViewById(R.id.checkedTextView)
        val checkedTextView2: CheckedTextView = findViewById(R.id.checkedTextView2)
        val checkedTextView3: CheckedTextView = findViewById(R.id.checkedTextView3)
        val checkedTextView4: CheckedTextView = findViewById(R.id.checkedTextView4)
        val checkedTextView5: CheckedTextView = findViewById(R.id.checkedTextView5)
        val checkedTextView6: CheckedTextView = findViewById(R.id.checkedTextView6)

        checkedTextView.setOnClickListener(listener1)
        checkedTextView2.setOnClickListener(listener1)
        checkedTextView3.setOnClickListener(listener1)
        checkedTextView4.setOnClickListener(listener2)
        checkedTextView5.setOnClickListener(listener2)
        checkedTextView6.setOnClickListener(listener2)
    }

    val listener1 = object: View.OnClickListener{
        override fun onClick(v: View?){
            // 형변환: v가 View 타입으로 들어오기 때문에 CheckedTextVeiw 타입으로 변환해주기
            val obj = v as CheckedTextView
            // 아래 if-else문 줄여서 obj.isChecked = obj.isChecked != true로 사용 가능
            if(obj.isChecked == true){
                obj.isChecked = false
            }else{
                obj.isChecked = true
            }
        }
    }

    val listener2 = object:View.OnClickListener{
        override fun onClick(v:View?){
            val checkedTextView4: CheckedTextView = findViewById(R.id.checkedTextView4)
            val checkedTextView5: CheckedTextView = findViewById(R.id.checkedTextView5)
            val checkedTextView6: CheckedTextView = findViewById(R.id.checkedTextView6)
            checkedTextView4.isChecked = false
            checkedTextView5.isChecked = false
            checkedTextView6.isChecked = false

            val obj = v as CheckedTextView
            obj.isChecked = true

        }
    }
}
