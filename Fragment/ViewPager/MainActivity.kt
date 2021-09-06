package kr.co.yeaeun.viewbasic

import android.os.*
import android.view.ViewParent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val p = findViewById<ViewPager>(R.id.pager)
        val adapter = MyPagerAdapter(supportFragmentManager)
        p.adapter = adapter
    }

    inner class MyPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        var fragments: ArrayList<Fragment>
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(i: Int): Fragment {
            return fragments[i]
        }

        init {
            fragments = ArrayList()
            fragments.add(OneFragment())
            fragments.add(ThreeFragment())
        }
    }
}