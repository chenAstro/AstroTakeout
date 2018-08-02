package com.astro.astrotakeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astro.astrotakeout.R
import com.astro.astrotakeout.ui.adapter.HomeRvAdapter
import org.jetbrains.anko.find

/**
 * Created by chenAstro at 下午1:27 on 2018/8/2.
 * @Description
 */
class HomeFragment : Fragment() {

    private val datas = ArrayList<String>()
    private lateinit var homeRvAdapter: HomeRvAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = View.inflate(activity, R.layout.fragment_home, null)
        initView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    private fun initData() {
        for (i in 0 until 100) {
            datas.add("我是商家  $i")
        }

        homeRvAdapter.setData(datas)
    }

    private fun initView(view: View) {
        val rvHome = view.find<RecyclerView>(R.id.rv_home)
        rvHome.layoutManager = LinearLayoutManager(activity)
        homeRvAdapter = HomeRvAdapter(activity)
        rvHome.adapter = homeRvAdapter
    }
}