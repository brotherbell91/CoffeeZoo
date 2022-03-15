package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.adapters.MainViewPagerAdapter
import com.hyeongjong.coffeezoo.databinding.ActivityMainBinding

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

        binding.btnMainSearch.setOnClickListener {

            val myIntent = Intent(this, SearchBarActivity::class.java)
            val inputSentence = binding.btnMainSearch.text.toString()
            myIntent.putExtra("inputSentence", inputSentence)
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