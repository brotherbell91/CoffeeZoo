package com.hyeongjong.coffeezoo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hyeongjong.coffeezoo.fragments.ThisMonthListFragment
import com.hyeongjong.coffeezoo.fragments.ReviewListFragment

class MainHomeFragmentViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount() = 2


    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> ThisMonthListFragment()
            else -> ReviewListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "이달의 카페"
            else -> "인기 리뷰"
        }

    }
}