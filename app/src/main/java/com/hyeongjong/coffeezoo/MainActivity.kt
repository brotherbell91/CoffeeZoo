package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.adapters.MainViewPagerAdapter
import com.hyeongjong.coffeezoo.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mMainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupEvents()
        setValues()

    }

    override fun setupEvents(){
//      btnMainSearch에 text입력 막기
        binding.btnMainSearch.isFocusable = false

        binding.btnMainSearch.setOnClickListener {

            val myIntent = Intent(this, SearchListActivity::class.java)

            startActivity(myIntent)


        }

    }

    override fun setValues(){
        mMainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        binding.mainViewPager.adapter = mMainViewPagerAdapter
        binding.mainViewPager.offscreenPageLimit = 2
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }

}