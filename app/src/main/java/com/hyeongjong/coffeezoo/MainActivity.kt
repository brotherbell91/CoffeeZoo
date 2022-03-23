package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.adapters.MainViewPager2Adapter
import com.hyeongjong.coffeezoo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mvp2a: MainViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupEvents()
        setValues()

    }

    override fun setupEvents(){
//      btnMainSearch에 text입력 막기
        binding.btnMainSearch.isFocusable = false

        binding.btnMainSearch.setOnClickListener {

            val myIntent = Intent(this, SearchListActivity::class.java)

            startActivity(myIntent)

        }

        //        바텀 네비게이션의 이벤트 처리.
        binding.mainBottomNav.setOnItemSelectedListener {

//            it 변수 : 선택된 메뉴가 뭔지? 알려줌.
//            it변수의 id값에 따라, 페이지 이동.

            binding.mainViewPager2.currentItem = when ( it.itemId) {
                R.id.home -> {
//                    홈버튼 누르면 검색바 표시
                    binding.btnMainSearch.isVisible = true
                    0
                }
                else ->
                {
//                    마이페이지 누르면 검색바 숨기기
                    binding.btnMainSearch.isVisible = false
                    1
                }

            }

            return@setOnItemSelectedListener true
        }


    }

    override fun setValues(){
        mvp2a = MainViewPager2Adapter(this)
        binding.mainViewPager2.adapter = mvp2a
        binding.mainViewPager2.offscreenPageLimit = 2
//        뷰페이저2 Swipe막기
        binding.mainViewPager2.setUserInputEnabled(false);
//        메인화면에 검색바 표시
        binding.btnMainSearch.isVisible = true

    }

}