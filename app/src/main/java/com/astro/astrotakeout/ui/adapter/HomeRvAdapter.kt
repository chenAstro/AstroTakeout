package com.astro.astrotakeout.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.astro.astrotakeout.R
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import org.jetbrains.anko.find
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by chenAstro at 下午3:25 on 2018/8/2.
 * @Description
 */
class HomeRvAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_SELLER = 1
    }

    private var mDatas = ArrayList<String>()

    fun setData(data: ArrayList<String>) {
        mDatas = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_TITLE
        } else {
            TYPE_SELLER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> TitleItemHolder(View.inflate(context, R.layout.item_title, null))
            TYPE_SELLER -> SellerItemHolder(View.inflate(context, R.layout.item_seller, null))
            else -> SellerItemHolder(View.inflate(context, R.layout.item_title, null))
        }

    }

    override fun getItemCount(): Int {
        return if (mDatas.size > 0) {
            mDatas.size + 1
        } else {
            0
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            TYPE_TITLE -> (holder as TitleItemHolder).bindData("我是头布局")
            TYPE_SELLER -> (holder as SellerItemHolder).bindData(mDatas[position - 1])
            else -> (holder as SellerItemHolder).bindData(mDatas[position - 1])
        }

    }

    inner class TitleItemHolder(item: View) : RecyclerView.ViewHolder(item) {
        val urlMaps = HashMap<String, String>()

        private val sliderLayout: SliderLayout = item.find(R.id.slider)

        fun bindData(s: String) {
            if (urlMaps.isEmpty()) {
                urlMaps["Hannibal"] = "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg"
                urlMaps["Big Bang Theory"] = "http://tvfiles.alphacoders.com/100/hdclearart-10.png"
                urlMaps["House of Cards"] = "http://cdn3.nflximg.net/images/3093/2043093.jpg"
                urlMaps["Game of Thrones"] = "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg"

                for ((key, value) in urlMaps) {
                    val textSliderView = TextSliderView(context)
                    textSliderView.description(key)
                            .image(value)
                    sliderLayout.addSlider(textSliderView)
                }
            }
        }
    }


    inner class SellerItemHolder(item: View) : RecyclerView.ViewHolder(item) {

//        private val textView: TextView = item.find(R.id.tv)

        fun bindData(s: String) {
//            textView.text = s
        }
    }
}