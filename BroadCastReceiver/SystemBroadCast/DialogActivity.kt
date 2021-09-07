package kr.co.yeaeun.viewbasic

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView


class DialogActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_dialog)
        val numberView = findViewById<TextView>(R.id.lab1_phone_number)
        val finishBtn = findViewById<ImageView>(R.id.lab1_remove_icon)
        finishBtn.setOnClickListener(this)
        val intent = intent
        val number = intent.getStringExtra("number")
        numberView.setText(number)
    }

    override fun onClick(v: View) {
        finish()
    }
}



