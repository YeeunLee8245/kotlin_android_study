package kr.co.yeaeun.viewbasic

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_read_file.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class ReadFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_file)

        try {
            // 경로와 파일 이름이 존재하는지, 올바른지 꼭 제대로 확인하자.
            val file: File = File(getExternalFilesDir(null)?.absolutePath+"/myApp/myfile.txt")//(getExternalFilesDir(null)?.absolutePath+"/myApp/myfile.txt")
            Log.d("file_test","${file.toString()}")
            val reader: BufferedReader = BufferedReader(FileReader(file))
            Log.d("reader_test","${reader.toString()}")
            var buffer = StringBuffer()
            var line: String?= reader.readLine()
            while(line != null){
                buffer.append(line)
                line = reader.readLine()
                Log.d("test","${buffer.toString()}")
            }
            fileResult.setText(buffer.toString())
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
