package com.astro.astrotakeout.ui.activity

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.astro.astrotakeout.R
import com.astro.astrotakeout.ui.fragment.HomeFragment
import com.astro.astrotakeout.ui.fragment.MoreFragment
import com.astro.astrotakeout.ui.fragment.OrderFragment
import com.astro.astrotakeout.ui.fragment.UserFragment
import com.astro.astrotakeout.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val fragments = listOf<Fragment>(HomeFragment(), OrderFragment(), UserFragment(), MoreFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {

        initNavigationBar()

        initMainBottomBar()

        changeIndex(0)

    }

    private fun initNavigationBar() {
        if (Utils.checkDeviceHasNavigationBar(this)) {
            ll_main_activity.setPadding(0, 0, 0, Utils.dp2px(this, 50))
        }
    }

    private fun initMainBottomBar() {
        for (i in 0 until ll_main_bottom_bar.childCount) {
            ll_main_bottom_bar.getChildAt(i).setOnClickListener {
                changeIndex(i)
            }
        }
    }

    private fun changeIndex(index: Int) {
        for (i in 0 until ll_main_bottom_bar.childCount) {
            if (i == index) {
                // When the view's status is selected  -> isEnable = false
                setEnable(ll_main_bottom_bar.getChildAt(i), false)
            } else {
                setEnable(ll_main_bottom_bar.getChildAt(i), true)
            }
        }

        fragmentManager.beginTransaction().replace(R.id.fl_layout_content, fragments[index]).commit()

    }

    private fun setEnable(view: View, isEnable: Boolean) {
        view.isEnabled = isEnable
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                view.getChildAt(i).isEnabled = isEnable
            }
        }
    }
}
