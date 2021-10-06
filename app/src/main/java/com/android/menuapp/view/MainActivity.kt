package com.android.menuapp.view

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.menuapp.R
import com.android.menuapp.databinding.ActivityMainBinding
import com.android.menuapp.util.DateUtils
import com.android.menuapp.util.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: DayPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListeners()

        viewModel.convertJsonToObject(this) {
            if (it) {
                bindDateText(viewModel.menuByDate[0].first.time)
                initViewPager()
            } else {
                showToast("Json dönüştürme başarısız oldu.")
                finish()
            }
        }

        viewModel.vpPositionLive.observe(this) {
            viewModel.vpPosition = it
            if (it == 0) {
                binding.ivMainPrev.setImageResource(R.drawable.arrow_left)
                binding.ivMainPrev.scaleX = 1F
            } else if (it > 0 && it < viewModel.menuByDate.size - 1) {
                binding.ivMainPrev.setImageResource(R.drawable.arrow_right)
                binding.ivMainPrev.scaleX = -1F
                binding.ivMainNext.setImageResource(R.drawable.arrow_right)
                binding.ivMainNext.scaleX = 1F
            } else {
                binding.ivMainPrev.setImageResource(R.drawable.arrow_right)
                binding.ivMainPrev.scaleX = -1F
                binding.ivMainNext.setImageResource(R.drawable.arrow_left)
                binding.ivMainNext.scaleX = -1F
            }
            binding.vpMain.currentItem = it
        }


    }


    private fun initViewPager() {
        pagerAdapter = DayPagerAdapter(context = this, viewModel.menuByDate)
        binding.vpMain.adapter = pagerAdapter

        binding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                bindDateText(viewModel.menuByDate[position].first.time)
                viewModel.vpPositionLive.postValue(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
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

    private fun initClickListeners() {
        binding.ivMainNext.setOnClickListener {
            if (viewModel.vpPosition < viewModel.menuByDate.size - 1) viewModel.vpPositionLive.postValue(
                viewModel.vpPosition + 1
            )
        }
        binding.ivMainPrev.setOnClickListener {
            if (viewModel.vpPosition > 0) viewModel.vpPositionLive.postValue(viewModel.vpPosition - 1)
        }
    }
}