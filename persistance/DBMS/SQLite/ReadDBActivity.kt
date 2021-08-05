package kr.co.yeaeun.viewbasic

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_read_db.*

class ReadDBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_db)

        var helper = DBHelper(this)
        var db: SQLiteDatabase = helper.writableDatabase
        var cursor : Cursor = db.rawQuery("select title, content from tb_memo order by _id desc limit 1", null)
        while(cursor.moveToNext()){
            read_title.setText(cursor.getString(0))
            read_content.setText(cursor.getString(1))
        }
        db.close()
    }
}