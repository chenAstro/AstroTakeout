package com.astro.astrotakeout.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Half.toFloat
import android.util.TypedValue
import java.lang.Class.forName

/**
 * Created by chenAstro at 下午2:21 on 2018/8/2.
 * @Description
 */
object Utils {


    /**
     * 判断设备是否具备底部虚拟按键
     */
    @SuppressLint("PrivateApi")
    fun checkDeviceHasNavigationBar(context: Context): Boolean {
        var hasNavigationBar = false
        val identifier = context.resources.getIdentifier("config_showNavigationBar", "bool", "android")
        if (identifier > 0) {
            hasNavigationBar = context.resources.getBoolean(identifier)
        }

        val clazz = forName("android.os.SystemProperties")
        val method = clazz.getMethod("get", String::class.java)
        val s = method.invoke(clazz, "qemu.hw.mainkeys") as String
        if (s == "1") {
            hasNavigationBar = false
        } else if (s == "0") {
            hasNavigationBar = true
        }

        return hasNavigationBar
    }

    /**
     * 把转化功能添加到Int类中作为扩展函数
     */
    fun dp2px(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()

    }
}