package com.android.menuapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.android.menuapp.R
import com.bumptech.glide.Glide

class DayPagerAdapter(
    private val context: Context,
) :
    PagerAdapter() {
    override fun getCount(): Int {
        return 5
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view: ViewGroup =
            layoutInflater.inflate(R.layout.item_day_pager, container, false) as ViewGroup



        container.addView(view)
        return view
    }
}