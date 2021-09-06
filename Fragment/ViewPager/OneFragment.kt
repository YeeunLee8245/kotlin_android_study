package kr.co.yeaeun.viewbasic

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment


class OneFragment : ListFragment(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var datas = arrayOf("박찬호", "류현진", "김현수", "오승환")
        val aa: ArrayAdapter<String> = ArrayAdapter<String>(activity!!,android.R.layout.simple_list_item_1,datas)
        listAdapter = aa
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val toat = Toast.makeText(activity!!,l.adapter.getItem(position) as String, Toast.LENGTH_SHORT)
        toat.show()
    }
}