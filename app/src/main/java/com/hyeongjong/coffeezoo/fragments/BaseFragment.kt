package com.hyeongjong.coffeezoo.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hyeongjong.coffeezoo.BaseActivity

abstract class BaseFragment : Fragment() {

    lateinit var mContext : Context


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

    }

    abstract fun setupEvents()
    abstract fun setValues()

}