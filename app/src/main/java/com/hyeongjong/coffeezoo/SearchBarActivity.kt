package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyeongjong.coffeezoo.adapters.SearchListAdapter
import com.hyeongjong.coffeezoo.app.OnCafeClickListener
import com.hyeongjong.coffeezoo.app.OnSearchClickListener
import com.hyeongjong.coffeezoo.databinding.ActivitySearchBarBinding
import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.SearchData

class SearchBarActivity : BaseActivity() {

    lateinit var binding : ActivitySearchBarBinding
    var mSearchList = ArrayList<SearchData>()
    lateinit var mSearchListAdapter : SearchListAdapter

    val listener = object : OnSearchClickListener {
        override fun onSearchClickEvent(search: SearchData) {
            binding.btnSearchAllDelete.setOnClickListener {  }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_bar)

        setupEvents()
        setValues()
        getUserList()

    }

    override fun setupEvents() {
//      EDITTEXT 검색기능 막기
        binding.EdtMainSearch.requestFocus()

    }

    override fun setValues() {


    }

    fun getUserList() {
//        리사이클러뷰 연결
        binding.recyclerViewSearchList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mSearchList = SearchData.latelySearchList()

        binding.recyclerViewSearchList.setHasFixedSize(true)

        mSearchListAdapter = SearchListAdapter(mSearchList)

        binding.recyclerViewSearchList.adapter = mSearchListAdapter

        mSearchListAdapter.setListener(listener)


    }

}