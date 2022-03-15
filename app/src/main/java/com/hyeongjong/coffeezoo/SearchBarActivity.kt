package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyeongjong.coffeezoo.adapters.CafeStoreListAdapter
import com.hyeongjong.coffeezoo.app.OnClickListener
import com.hyeongjong.coffeezoo.databinding.ActivitySearchBarBinding
import com.hyeongjong.coffeezoo.datas.CafeData

class SearchBarActivity : BaseActivity() {

    lateinit var binding : ActivitySearchBarBinding
    var mCafeStoreList = ArrayList<CafeData>()
    lateinit var mCafeStoreListAdapter : CafeStoreListAdapter

    val listener = object : OnClickListener{
        override fun onClickEvent(cafe: CafeData) {
            val clickedCafe = cafe
            val myIntent = Intent(mContext, CafeDetailViewActivity::class.java)
            myIntent.putExtra("clickedCafeDetail", clickedCafe)
            startActivity(myIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_bar)

        setupEvents()
        setValues()
        getUserList()

        binding.EdtMainSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
//                메소드 호출 후 검색어 전달
                filter(editable.toString())
            }
        })

    }

    override fun setupEvents() {

    }

    override fun setValues() {


    }

    fun getUserList() {
//        리사이클러뷰 연결
        binding.recyclerViewStoreList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mCafeStoreList = CafeData.thisMonthList()

        binding.recyclerViewStoreList.setHasFixedSize(true)
        mCafeStoreListAdapter = CafeStoreListAdapter(mCafeStoreList)

        binding.recyclerViewStoreList.adapter = mCafeStoreListAdapter

        mCafeStoreListAdapter.setListener(listener)

    }

    fun filter(text: String) {
//        검색된 데이터 정렬
        val filteredNames = ArrayList<CafeData>()
//        필터된 리스트 데이터 추가
        mCafeStoreList.filterTo(filteredNames) {
//        존제하는 데이터 넣기
            it.cafeName.toLowerCase().contains(text.toLowerCase())
        }
//        어댑터 클래스의 메서드 호출 및 필터링된 목록 전달
        mCafeStoreListAdapter!!.filterList(filteredNames)
    }

}