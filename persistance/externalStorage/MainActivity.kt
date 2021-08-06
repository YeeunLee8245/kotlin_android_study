package kr.co.yeaeun.viewbasic

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var fileReadPermission: Boolean = false
    var fileWritePermission: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
            fileReadPermission = true
        }
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
            fileWritePermission = true
        }
        // permission 부여 안 될 경우 permission 요청
        if(!fileReadPermission || !fileWritePermission){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE),200)
        }
        btn.setOnClickListener {
            var content = content.text.toString()
            if(fileReadPermission && fileWritePermission){
                try{
                    val dirPath: String = getExternalFilesDir(null)?.absolutePath+"/myApp"
                    val dir = File(dirPath)
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    var file: File = File(dir.toString()+"/myfile.txt")
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    var writer:FileWriter = FileWriter(file, true)
                    writer.write(content)
                    writer.flush()
                    writer.close()

                    val intent: Intent=Intent(this, ReadFileActivity::class.java);
                    startActivity(intent)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }else{
                val toast = Toast.makeText(applicationContext, "permission이 부여되지 않았습니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 200 && grantResults.size > 0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                fileReadPermission=true;
            if(grantResults[1]==PackageManager.PERMISSION_GRANTED)
                fileWritePermission=true;
        }
    }


}