package com.hyeongjong.coffeezoo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.databinding.ActivityCafeDetailViewBinding
import com.hyeongjong.coffeezoo.datas.CafeData


class CafeDetailViewActivity : BaseActivity() {

    lateinit var binding: ActivityCafeDetailViewBinding

    lateinit var mCafeData : CafeData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cafe_detail_view)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {





    }

    override fun setValues() {

        mCafeData = intent.getSerializableExtra("clickedCafeDetail") as CafeData

        binding.txtDetailCafeName.text = mCafeData.cafeName
        binding.txtDetailCafeAddress.text = mCafeData.cafeAddress
        binding.txtDetailCafeNumber.text = mCafeData.cafeNumber
        binding.txtDetailCafeDescription.text = mCafeData.cafeDescription
        binding.ratingBarDetailCafeScore.rating = mCafeData.score.toFloat()

    }
}