package kr.co.yeaeun.viewbasic

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnMyChangeListener {
    var barView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val plusMinusView = findViewById<MyPlusMinusView>(R.id.customView)
        barView = findViewById(R.id.barView)
        plusMinusView.setOnMyChangeListener(this)
    }

    override fun onChange(value: Int) {
        if (value < 0) {
            barView!!.setBackgroundColor(Color.RED)
        } else if (value < 30) {
            barView!!.setBackgroundColor(Color.YELLOW)
        } else if (value < 60) {
            barView!!.setBackgroundColor(Color.BLACK)
        } else {
            barView!!.setBackgroundColor(Color.GREEN)
        }
    }
}