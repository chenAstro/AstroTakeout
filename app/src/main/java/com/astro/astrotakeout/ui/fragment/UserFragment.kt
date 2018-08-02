package com.astro.astrotakeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.astro.astrotakeout.R

/**
 * Created by chenAstro at 下午1:27 on 2018/8/2.
 * @Description
 */
class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = View.inflate(activity, R.layout.fragment_, null)
        (view as TextView).text = activity.getString(R.string.main_bottom_bar_tab_me)
        return view
    }
}