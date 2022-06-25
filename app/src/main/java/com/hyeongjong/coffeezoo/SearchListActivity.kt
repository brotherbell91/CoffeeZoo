package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyeongjong.coffeezoo.adapters.SearchListAdapter
import com.hyeongjong.coffeezoo.app.OnItemClick
import com.hyeongjong.coffeezoo.databinding.ActivitySearchListBinding
import com.hyeongjong.coffeezoo.datas.SearchData
import com.hyeongjong.coffeezoo.utils.ContextUtil
import java.text.SimpleDateFormat
import java.util.*

class SearchListActivity : BaseActivity() {

    lateinit var binding : ActivitySearchListBinding
    var mSearchList = ArrayList<SearchData>()
    lateinit var mAdapter : SearchListAdapter
    var mCurrentTime = System.currentTimeMillis()//현재시간 가져오기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_list)

        setupEvents()
        setValues()
        getSearchHistory()

    }

    override fun setupEvents() {
//      검색창에 포커스 넣기
        binding.EdtMainSearch.requestFocus()

        binding.EdtMainSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
//                에딧텍스트 엔터시 이벤트
                setSearchHistoryDate()

                mAdapter.notifyDataSetChanged()

            }
//            setOnKeyListener에 backPressed 이벤트가 들어있어 백버튼을 다시 만들어줘야 한다
            else if(keyCode==KeyEvent.KEYCODE_BACK){

                onBackPressed()

            }
            true
        }

    }

    override fun setValues() {

    }

    fun getSearchHistory() {
//      리싸이클러뷰 연결

        mSearchList = ContextUtil.getSearchHistory(mContext)

        mAdapter = SearchListAdapter(mContext, mSearchList)

        binding.searchListRecyclerView.adapter = mAdapter

        binding.searchListRecyclerView.layoutManager = LinearLayoutManager(mContext)

//        item 역순으로 정렬
        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        binding.searchListRecyclerView.setLayoutManager(linearLayoutManager)

        //                어답터에 클릭이벤트 처리
        mAdapter.oic = object : OnItemClick {
            override fun onItemClick(position: Int) {
//                쉐어드에 해당 position 데이터 삭제

                ContextUtil.deleteSearchHistory(mContext, mSearchList, position )

            }

        }

    }

    fun setSearchHistoryDate() {

        val mDate = Date(mCurrentTime)

        val sdf = SimpleDateFormat("MM-dd HH:ss")

        val mDateFormat = sdf.format(mDate)

        Toast.makeText(mContext, "${mDateFormat}", Toast.LENGTH_SHORT).show()
//                현제 시간 데이터
        val inputSearchDate = mDateFormat
//                검색어 데이터
        val inputSearchText = binding.EdtMainSearch.text.toString()
//                ArrayList에 데이터 저장
        mSearchList.add(SearchData(inputSearchText,inputSearchDate))
//                쉐어드 프리퍼런시스에 데이터 저장
        ContextUtil.setSearchHistory(mContext,mSearchList)

    }

}