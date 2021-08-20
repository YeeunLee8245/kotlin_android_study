package kr.co.yeaeun.viewbasic

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.PreferenceScreen


class SettingPreferenceFragment : PreferenceFragmentCompat() {
    lateinit var prefs: SharedPreferences
    lateinit var soundPreference: Preference
    lateinit var keywordSoundPreference: Preference
    lateinit var keywordScreen: Preference


    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        setPreferencesFromResource(R.xml.settings_preference, s)
        if (s == null) {
            soundPreference = findPreference("sound_list")
            keywordSoundPreference = findPreference("keyword_sound_list")
            keywordScreen = findPreference("keyword_screen")
            prefs = PreferenceManager.getDefaultSharedPreferences(activity)
            if (prefs!!.getString("sound_list", "") != "") {
                soundPreference.setSummary(prefs!!.getString("sound_list", "카톡"))
            }
            if (prefs!!.getString("keyword_sound_list", "") != "") {
                keywordSoundPreference.setSummary(prefs!!.getString("keyworld_sound_list", "카톡"))
            }
            if (prefs!!.getBoolean("keyword", false)) {
                keywordScreen.setSummary("사용")
            }
            prefs!!.registerOnSharedPreferenceChangeListener(prefListener)
        }
    }

    var prefListener =
        OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "sound_list") {
                soundPreference.setSummary(prefs!!.getString("sound_list", "카톡"))
            }
            if (key == "keyword_sound_list") {
                keywordSoundPreference.setSummary(prefs!!.getString("keyword_sound_list", "카톡"))
            }
        }

    override fun onNavigateToScreen(preferenceScreen: PreferenceScreen?) {
        super.onNavigateToScreen(preferenceScreen)
        val intent: Intent = Intent(activity, MainActivity::class.java).putExtra("TARGET_SETTING_PAGE", preferenceScreen?.key)
        startActivity(intent)
    }
}



