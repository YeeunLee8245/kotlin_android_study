package kr.co.yeaeun.viewbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

// manifests에 설정한 권한 중 거부되어있는(개인정보와 관련) 권한들만 승인을 받아야함
// manifests에 있는 권한 전부를 복사해서 넣어주면된다(알아서 걸러줌)
class MainActivity : AppCompatActivity() {

    val permission_list = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        for (permission in permission_list) {
            // 권한 허용 여부
            val chk = checkCallingOrSelfPermission(permission)

            if (chk == PackageManager.PERMISSION_GRANTED){
                textView.append("${permission} : 허용\n")
            } else if (chk == PackageManager.PERMISSION_DENIED){
                textView.append("${permission} : 거부\n")
            }
        }

        // 권한은 첫 Activity가 뜰 때 바로 검사한다.
        button.setOnClickListener {
            // 거부되어 있는 권한을 사용자에게 확인 받는다.
            requestPermissions(permission_list, 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        textView.text = ""

        for(idx in grantResults.indices){
            if (grantResults[idx] == PackageManager.PERMISSION_GRANTED){
                textView.append("${permissions[idx]} : 허용\n")
            } else if (grantResults[idx] == PackageManager.PERMISSION_DENIED){
                textView.append("${permissions[idx]} : 거부\n")
            }
        }
    }
}

//<uses-permission android:name="android.permission.INTERNET"/>
//<!-- 현재 위치 -->
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
//<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
//<!-- 연락처 접근 권한 -->
//<uses-permission android:name="android.permission.READ_CONTACTS"/>
//<uses-permission android:name="android.permission.WRITE_CONTACTS"/>
//<!-- 외부 저장소 사용 권한-->
//<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
//<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
