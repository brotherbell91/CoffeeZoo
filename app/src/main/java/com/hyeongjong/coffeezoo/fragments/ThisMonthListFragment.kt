package com.hyeongjong.coffeezoo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyeongjong.coffeezoo.CafeDetailViewActivity
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.adapters.ThisMonthListAdapter
import com.hyeongjong.coffeezoo.app.OnItemClick
import com.hyeongjong.coffeezoo.databinding.FragmentThisMonthListBinding
import com.hyeongjong.coffeezoo.datas.CafeData

class ThisMonthListFragment : BaseFragment() {

    lateinit var binding : FragmentThisMonthListBinding

    var mCafeStoreList = ArrayList<CafeData>()

    lateinit var mAdapter : ThisMonthListAdapter

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

//        임시 데이터 연결
        mCafeStoreList = CafeData.thisMonthList()
//        어답터에 데이터 연결
        mAdapter = ThisMonthListAdapter(mContext, mCafeStoreList)
//        어답터에 클릭이벤트 처리
        mAdapter.oic = object : OnItemClick{
            override fun onItemClick(position: Int) {

                val clickCafeStore = mCafeStoreList[position]

                val myIntent = Intent(mContext, CafeDetailViewActivity::class.java)
                myIntent.putExtra("clickedCafeDetail", clickCafeStore)
                startActivity(myIntent)

            }

        }

    }

    override fun setValues() {

//      리싸이클러뷰 연결
        binding.thisMonthRecyclerView.adapter = mAdapter
//      리싸이클러뷰 모양설정
        binding.thisMonthRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }


}