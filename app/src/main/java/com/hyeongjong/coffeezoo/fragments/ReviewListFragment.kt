package com.hyeongjong.coffeezoo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.adapters.ReviewListAdapter
import com.hyeongjong.coffeezoo.databinding.FragmentReviewListBinding
import com.hyeongjong.coffeezoo.lifecycle.ListViewModel

class ReviewListFragment : BaseFragment() {

    lateinit var binding : FragmentReviewListBinding

    lateinit var mAdapter : ReviewListAdapter

    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_list, container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()
        observerData()

    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mAdapter = ReviewListAdapter(mContext)
//      리싸이클러뷰 연결
        binding.photoListRecyclerView.adapter = mAdapter
//      리싸이클러뷰 모양설정
        binding.photoListRecyclerView.layoutManager = GridLayoutManager(mContext, 2)

    }

    fun observerData(){
//        fragment 에서 this 대신 viewLifecycleOwner 를 사용
        viewModel.fetchReviewData().observe(viewLifecycleOwner, Observer {
            mAdapter.setListData(it)
            mAdapter.notifyDataSetChanged()

        })

    }


}