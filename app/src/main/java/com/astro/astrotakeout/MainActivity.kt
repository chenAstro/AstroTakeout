package com.astro.astrotakeout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {

        initMainBottomBar()
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
