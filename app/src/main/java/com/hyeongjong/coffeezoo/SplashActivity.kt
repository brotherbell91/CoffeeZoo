package com.hyeongjong.coffeezoo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.Login.LoginMainActivity
import com.hyeongjong.coffeezoo.databinding.ActivitySplashBinding
import java.security.MessageDigest

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

        getKeyHash()

    }

//    키 해쉬값 추출
    fun getKeyHash() {

        val info = packageManager.getPackageInfo(
            "com.hyeongjong.coffeezoo",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }

    }
}