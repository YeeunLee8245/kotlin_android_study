package kr.co.yeaeun.viewbasic

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    var arrayDatas:Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayDatas = resources.getStringArray(R.array.location)
        val arrayAdater = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayDatas)
        arrayView.adapter = arrayAdater

        var simpleDatas:ArrayList<HashMap<String,String>> = ArrayList()
        val helper = DBHelper(this)
        val db = helper.writableDatabase
        val cursor = db.rawQuery("select * from tb_data",null)
        while(cursor.moveToNext()){
            var map = HashMap<String,String>()
            map.put("name",cursor.getString(1))
            map.put("content",cursor.getString(2))
            simpleDatas.add(map)
        }

        var adapter = SimpleAdapter(this, simpleDatas, android.R.layout.simple_list_item_2,
            arrayOf("name","content"), intArrayOf(android.R.id.text1,android.R.id.text2))
        simpleView.adapter = adapter

        var cursorAdapter:CursorAdapter = SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,
        cursor, arrayOf("name","content"), intArrayOf(android.R.id.text1, android.R.id.text2), CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
        cursorView.adapter = cursorAdapter

        arrayView.setOnItemClickListener { parent, view, position, id ->
            var t = Toast.makeText(this, arrayDatas[position], Toast.LENGTH_SHORT)
            t.show()
        }
    }



}