package com.astro.astrotakeout.presenter

import com.astro.astrotakeout.ui.fragment.HomeFragment

class HomeFragmentPresenter(private val homeFragment: HomeFragment) {


    fun getHomeFragmentDataInfo() {

        homeFragment.onLoadDatasSuccess()

        homeFragment.onLoadDatasFailed()

    }

}