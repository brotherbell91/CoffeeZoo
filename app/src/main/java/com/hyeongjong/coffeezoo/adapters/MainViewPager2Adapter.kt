package com.hyeongjong.coffeezoo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hyeongjong.coffeezoo.fragments.MainHomeFragment
import com.hyeongjong.coffeezoo.fragments.MainMyPageFragment


class MainViewPager2Adapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainHomeFragment()
            else -> MainMyPageFragment()
        }

    }
}