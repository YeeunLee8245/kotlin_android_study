package kr.co.yeaeun.viewbasic

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var title = add_title.text.toString()
            var content = add_content.text.toString()
            val helper = DBHelper(this)
            val db: SQLiteDatabase = helper.writableDatabase
            db.execSQL("insert into tb_memo (title, content) values (?,?)",
            arrayOf(title, content))
            db.close()

            val intent = Intent(this, ReadDBActivity::class.java)
            startActivity(intent)
        }
    }


}