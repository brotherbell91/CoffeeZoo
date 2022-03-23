package com.hyeongjong.coffeezoo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.adapters.MainHomeFragmentViewPagerAdapter
import com.hyeongjong.coffeezoo.databinding.FragmentMainHomeBinding

class MainHomeFragment : BaseFragment() {

    lateinit var mAdapter : MainHomeFragmentViewPagerAdapter

    lateinit var binding : FragmentMainHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_home,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {
//        프래그먼트 안에 프래그먼트를 생성시 supportFragmentManager 대신 childFragmentManager 사용
        mAdapter = MainHomeFragmentViewPagerAdapter(childFragmentManager)

        binding.mainHomeViewPager.adapter = mAdapter

        binding.mainHomeTabLayout.setupWithViewPager(binding.mainHomeViewPager)

        binding.mainHomeViewPager.offscreenPageLimit = 2



    }
}