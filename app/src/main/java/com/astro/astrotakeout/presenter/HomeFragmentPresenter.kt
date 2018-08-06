package com.astro.astrotakeout.presenter

import android.widget.Toast
import com.astro.astrotakeout.model.beans.Seller
import com.astro.astrotakeout.model.net.ResponseInfo
import com.astro.astrotakeout.model.net.TakeoutService
import com.astro.astrotakeout.ui.fragment.HomeFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragmentPresenter(private val homeFragment: HomeFragment) {


//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    private val takeoutService: TakeoutService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.101:8080/TakeoutService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        takeoutService = retrofit.create<TakeoutService>(TakeoutService::class.java)
    }

    fun getHomeFragmentDataInfo() {

        val homeInfoCall = takeoutService.getHomeInfoData()
        homeInfoCall.enqueue(object : Callback<ResponseInfo> {
            override fun onFailure(call: Call<ResponseInfo>?, t: Throwable?) {
                Toast.makeText(homeFragment.activity, "服务器链接错误", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseInfo>?, response: Response<ResponseInfo>?) {
                if (response == null) {
                    Toast.makeText(homeFragment.activity, "服务器返回错误", Toast.LENGTH_LONG).show()
                } else {
                    if (response.isSuccessful) {
                        val responseInfo = response.body()
                        if (responseInfo.code == "0") {
                            val data = responseInfo.data
                            parserData(data)
                        } else {
                            Toast.makeText(homeFragment.activity, "服务器返回错误", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        })


    }

    private fun parserData(data: String?) {
        val gson = Gson()

        val dataObject = JSONObject(data)
        val nearbyString = dataObject.optString("nearbySellerList")
        val otherString = dataObject.optString("otherSellerList")

        val nearbySellers = gson.fromJson<List<Seller>>(nearbyString, object : TypeToken<List<Seller>>() {}.type)
        val otherSellers = gson.fromJson<List<Seller>>(otherString, object : TypeToken<List<Seller>>() {}.type)

        if (nearbySellers.isNotEmpty() || otherSellers.isNotEmpty()) {
            homeFragment.onLoadDataSuccess()
        } else {
            homeFragment.onLoadDataFailed()
        }

    }

}


