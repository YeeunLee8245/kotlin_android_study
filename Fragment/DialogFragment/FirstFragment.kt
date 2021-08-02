package kr.co.yeaeun.viewbasic

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

// Dialog를 관리하는 Fragment
class FirstFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val mainActivity = activity as MainActivity

        val builder = AlertDialog.Builder(mainActivity)
        builder.setTitle("타이틀 입니다.")
        builder.setMessage("메시지 입니다.")

        builder.setPositiveButton("Positive"){dialogInterface, i ->
            mainActivity.textView.text = "Positive"
        }

        builder.setNeutralButton("Neutral"){dialogInterface, i ->
            mainActivity.textView.text = "Neutral"
        }

        builder.setNegativeButton("Negative"){dialogInterface, i ->
            mainActivity.textView.text = "Negative"
        }
        // alertDialog 생성
        val alert = builder.create()
        return alert
    }

}
