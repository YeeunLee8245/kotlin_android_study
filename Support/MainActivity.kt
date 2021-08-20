package kr.co.yeaeun.viewbasic

import android.content.Intent
import android.content.res.Configuration
import android.os.*
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsFragment = SettingPreferenceFragment()
        val intent:Intent? = getIntent() // 실행된 자신의 정보

        if(intent != null){
            val rootKey: String? = intent.getStringExtra("TARGET_SETTING_PAGE") // extra data 추출
            if (rootKey != null){
                val args = Bundle()
                args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, rootKey)
                settingsFragment.arguments = args
            }
        }

        supportFragmentManager.beginTransaction().replace(android.R.id.content, settingsFragment, null)
            .commit()
    }


}