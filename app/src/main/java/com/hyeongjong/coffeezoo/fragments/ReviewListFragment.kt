package com.hyeongjong.coffeezoo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.adapters.ReviewListAdapter
import com.hyeongjong.coffeezoo.databinding.FragmentReviewListBinding
import com.hyeongjong.coffeezoo.datas.ReviewListData

class ReviewListFragment : BaseFragment() {

    lateinit var binding : FragmentReviewListBinding

    var mPhotoList = ArrayList<ReviewListData>()

    lateinit var mAdapter : ReviewListAdapter

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

    }

    override fun setupEvents() {

    }

    override fun setValues() {
//        임시 데이터 연결
        mPhotoList = ReviewListData.photoList()
//        어답터에 데이터 연결
        mAdapter = ReviewListAdapter(mContext, mPhotoList)
//      리싸이클러뷰 연결
        binding.photoListRecyclerView.adapter = mAdapter
//      리싸이클러뷰 모양설정
        binding.photoListRecyclerView.layoutManager = GridLayoutManager(mContext, 2)

    }


}