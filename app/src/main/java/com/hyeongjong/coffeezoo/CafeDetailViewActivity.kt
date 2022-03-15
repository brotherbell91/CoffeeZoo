package com.hyeongjong.coffeezoo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.databinding.ActivityCafeDetailViewBinding
import com.hyeongjong.coffeezoo.datas.CafeData


class CafeDetailViewActivity : BaseActivity() {

    lateinit var binding: ActivityCafeDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cafe_detail_view)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val cafeData = intent.getSerializableExtra("clickedCafeDetail") as CafeData

        binding.txtDetailCafeName.text = cafeData.cafeName
        binding.txtDetailCafeAddress.text = cafeData.cafeAddress
        binding.txtDetailCafeDescription.text = cafeData.cafeDescription
        binding.ratingBarDetailCafeScore.rating = cafeData.score.toFloat()

    }
}