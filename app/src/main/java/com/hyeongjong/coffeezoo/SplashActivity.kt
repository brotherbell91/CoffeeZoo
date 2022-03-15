package com.hyeongjong.coffeezoo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.Login.LoginMainActivity
import com.hyeongjong.coffeezoo.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed( {
            val myIntent = Intent(this, LoginMainActivity::class.java)
            startActivity(myIntent)

            finish()

        },3000)

    }

    override fun setValues() {

    }
}