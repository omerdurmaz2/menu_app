package com.android.menuapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.android.menuapp.R
import com.android.menuapp.model.Fields
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.util.*

class DayPagerAdapter(
    private val context: Context,
    private val list: List<Pair<Date, List<Fields>>>?
) :
    PagerAdapter() {
    override fun getCount(): Int {
        return list?.size ?: 0
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
        val containerView: LinearLayout = view.findViewById(R.id.llItemDayPager)

        val item = list?.get(position)
        val categories = item?.second?.groupBy { it.foodCategory }?.toList()?.sortedBy { it.first }
        categories?.forEach { category ->
            val textview = TextView(context)
            textview.text = category.first
            containerView.addView(textview)
            category.second.forEach {
                containerView.addView(createFoodItem(layoutInflater, container, it))
            }
        }


        container.addView(view)
        return view
    }

    private fun createFoodItem(
        layoutInflater: LayoutInflater,
        container: ViewGroup,
        field: Fields
    ): View {
        val view: ViewGroup =
            layoutInflater.inflate(R.layout.item_menu_food, container, false) as ViewGroup
        view.findViewById<TextView>(R.id.tvItemMenuFoodTitle).text = field.title
        view.findViewById<TextView>(R.id.tvItemMenuFoodKcal).text = field.calorie.toString()
        return view
    }
}