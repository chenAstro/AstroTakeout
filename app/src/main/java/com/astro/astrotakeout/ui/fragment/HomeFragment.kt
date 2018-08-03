package com.astro.astrotakeout.ui.fragment

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astro.astrotakeout.R
import com.astro.astrotakeout.ui.adapter.HomeRvAdapter
import com.astro.astrotakeout.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find

/**
 * Created by chenAstro at 下午1:27 on 2018/8/2.
 * @Description
 */
class HomeFragment : Fragment() {

    private val datas = ArrayList<String>()
    private lateinit var homeRvAdapter: HomeRvAdapter
    private lateinit var rvHome: RecyclerView

    private var sum = 0
    private var distance = 0
    private var alpha = 55

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

        distance = Utils.dp2px(activity, 120)

        homeRvAdapter.setData(datas)

        rvHome.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                sum += dy

                if (sum > distance) {
                    alpha = 255
                } else {
                    alpha = sum * 200 / distance
                    alpha += 55
                }

                ll_title_container.setBackgroundColor(Color.argb(alpha, 0x31, 0x90, 0xe8))

            }
        })

    }

    private fun initView(view: View) {
        rvHome = view.find<RecyclerView>(R.id.rv_home)
        rvHome.layoutManager = LinearLayoutManager(activity)
        homeRvAdapter = HomeRvAdapter(activity)
        rvHome.adapter = homeRvAdapter
    }
}