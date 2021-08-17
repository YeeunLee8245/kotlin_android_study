package kr.co.yeaeun.viewbasic

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        keyboardBtn.setOnClickListener {
            // 키보드 올라옴
            val manager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            // 기존 상태 toggle이기 때문에 이 경우에서는 키보드를 사라지게 함
            manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }


    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onResume() {
        super.onResume()
        showToast("onResume.....")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (isInMultiWindowMode) {
                showToast("onResume.... isInMultiWindowMode...yes")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        showToast("onPause")
    }

    // 분할 화면 기능을 사용하려고할 때와 해제할 때
    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
        showToast("onMultiWindowModeChanged...$isInMultiWindowMode")
    }

    // 액티비티의 각종 환경이 변경되었을 때(Mainfest에서 속성 변경하여 주로 함께 사용)
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showToast("portrait....")
        } else {
            showToast("landscape....")
        }
    }
}