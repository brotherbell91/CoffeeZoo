package com.hyeongjong.coffeezoo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.adapters.ThisMonthListAdapter
import com.hyeongjong.coffeezoo.databinding.FragmentThisMonthListBinding
import com.hyeongjong.coffeezoo.datas.CafeData

class ThisMonthListFragment : BaseFragment() {

    lateinit var binding : FragmentThisMonthListBinding

    var mCafeStoreList = ArrayList<CafeData>()

    lateinit var mAdapter : ThisMonthListAdapter

//    val listener = object : OnCafeClickListener{
//        override fun onCafeClickEvent(cafe: CafeData) {
//            val clickedCafe = cafe
//            val myIntent = Intent(requireContext(), CafeDetailViewActivity::class.java)
//            myIntent.putExtra("clickedCafeDetail", clickedCafe)
//            startActivity(myIntent)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_this_month_list,container,false)
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
//      리싸이클러뷰 연결
        //        임시 카페 데이터
        mCafeStoreList = CafeData.thisMonthList()

        mAdapter = ThisMonthListAdapter(mContext, mCafeStoreList)

        binding.thisMonthRecyclerView.adapter = mAdapter

        binding.thisMonthRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }
}