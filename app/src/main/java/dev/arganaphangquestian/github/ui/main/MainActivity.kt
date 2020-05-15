package dev.arganaphangquestian.github.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.utils.BOTTOMMENU
import kotlinx.android.synthetic.main.activity_main.*
import me.ibrahimsn.lib.OnItemSelectedListener

class MainActivity : AppCompatActivity() {

    private val mainAdapter = MainAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setupFragment()
    }

    private fun init() {
        setContentView(R.layout.activity_main)
    }

    private fun setupFragment() {
        mainAdapter.setData(BOTTOMMENU)
        vp_main.apply {
            adapter = mainAdapter
        }
        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // Do Something
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Do Something
            }

            override fun onPageSelected(position: Int) {
                sbb_main.setActiveItem(position)
                rrv_main.translationY = 0f
            }
        })

        sbb_main.setActiveItem(0)
        sbb_main.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelect(pos: Int): Boolean {
                vp_main.currentItem = pos
                return true
            }
        })
    }
}