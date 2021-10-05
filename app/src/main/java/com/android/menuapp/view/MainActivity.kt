package com.android.menuapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.menuapp.databinding.ActivityMainBinding
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

        viewModel.convertJsonToObject(this) {
            if (it) {
                initViewPager()
            } else {
                showToast("Json dönüştürme başarısız oldu.")
                finish()
            }
        }
    }

    private fun initViewPager() {
        pagerAdapter = DayPagerAdapter(context = this)
        binding.vpMain.adapter = pagerAdapter
    }
}