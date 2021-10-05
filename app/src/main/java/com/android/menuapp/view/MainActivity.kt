package com.android.menuapp.view

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.menuapp.databinding.ActivityMainBinding
import com.android.menuapp.util.DateUtils
import com.android.menuapp.util.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: DayPagerAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.convertJsonToObject(this) {
            if (it) {
                bindDateText(viewModel.menuByDate[0].first.time)
                initViewPager()
            } else {
                showToast("Json dönüştürme başarısız oldu.")
                finish()
            }
        }

        binding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                bindDateText(viewModel.menuByDate?.get(position)?.first?.time)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun initViewPager() {
        pagerAdapter = DayPagerAdapter(context = this, viewModel.menuByDate)
        binding.vpMain.adapter = pagerAdapter
    }

    private fun bindDateText(date: Long) {
        DateUtils.apply {
            binding.tvMainDate.text = date.let {
                getDayNumber(it).plus(" ").plus(
                    getMonthName(it).plus(" ").plus(getDayName(it))
                )
            }
        }
    }
}