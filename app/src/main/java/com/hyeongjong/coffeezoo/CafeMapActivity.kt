package com.hyeongjong.coffeezoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.databinding.ActivityCafeMapBinding

class CafeMapActivity : BaseActivity() {

    lateinit var binding : ActivityCafeMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cafe_map)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}