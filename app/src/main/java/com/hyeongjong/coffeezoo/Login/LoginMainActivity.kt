package com.hyeongjong.coffeezoo.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.BaseActivity
import com.hyeongjong.coffeezoo.MainActivity
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.databinding.ActivityLoginMainBinding

class LoginMainActivity : BaseActivity() {

    lateinit var binding : ActivityLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_main)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

//        카카오 로고 클릭시 > 카카오 로그인

        binding.imgKakao.setOnClickListener {



        }

//        로그인 버튼 구현
        binding.btnLogin.setOnClickListener {
            var inputId = binding.edtInputId.text.toString()
            var inputPw = binding.edtInputPw.text.toString()

            val myIntent = Intent(this,MainActivity::class.java)

//            맞다면
            if (inputId == "admin" && inputPw == "qwer") {

                Toast.makeText(this, "관리자님 환영합니다.", Toast.LENGTH_SHORT).show()

                startActivity(myIntent)

            }
//            아이디가 틀리다면
            else if(inputId != "admin") {

                Toast.makeText(this, "잘못된 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
//            아이디는 맞고 비밀번호가 틀리다면
            else {

                Toast.makeText(this, "잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun setValues() {

    }
}