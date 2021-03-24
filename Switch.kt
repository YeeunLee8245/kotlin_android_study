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

        val button:Button = findViewById(R.id.button)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)
        val switch1: Switch = findViewById(R.id.switch1)
        val switch2: Switch = findViewById(R.id.switch2)
        val textView:TextView = findViewById(R.id.textView)
        val textView2:TextView = findViewById(R.id.textView2)
        button.setOnClickListener {
            if(switch1.isChecked == true){
                textView.text = "ON 상태"
            }else{
                textView.text = "OFF 상태"
            }
        }
        button2.setOnClickListener {
            switch1.isChecked = true
        }
        button3.setOnClickListener {
            switch1.isChecked = false
        }
        switch1.setOnCheckedChangeListener(listener1)

    }


    val listener1 = object : CompoundButton.OnCheckedChangeListener{

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {    // buttonView: 바뀐 button
            val switch1: Switch = findViewById(R.id.switch1)
            val switch2: Switch = findViewById(R.id.switch2)
            val textView:TextView = findViewById(R.id.textView)
            val textView2:TextView = findViewById(R.id.textView2)
            when(buttonView?.id){
                R.id.switch1 -> {
                    if(isChecked == true){
                        textView.text = "첫 번째 스위치 ON"
                    }else{
                        textView.text = "두 번째 스위치 ON"
                    }
                }
            }
        }
    }
}
