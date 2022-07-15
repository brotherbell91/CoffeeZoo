package com.hyeongjong.coffeezoo.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.BaseActivity
import com.hyeongjong.coffeezoo.MainActivity
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.databinding.ActivityLoginMainBinding
import com.kakao.sdk.user.UserApiClient

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

//            카톡 앱이 깔려있으면? 앱으로 로그인, 아니면? 별도로 로그인
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)){

                Log.d("카톡로그인", "앱으로 로그인 가능")
//                카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->

//                    카톡 앱으로 로그인 되었을 때 할 코드
                    getUserInfo()

                }

            }
            else{

                Log.d("카톡로그인", "앱으로 로그인 불가 - 별도 로그인 필요")
//                카카오 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(mContext) { token, error ->

//                    카톡 앱이 없어서, 다른 방식으로 로그인 되었을 때 할 코드
                    getUserInfo()

                }

            }

        }

//        로그인 버튼 구현
        binding.btnLogin.setOnClickListener {
            var inputId = binding.edtInputId.text.toString()
            var inputPw = binding.edtInputPw.text.toString()

//            맞다면
            if (inputId == "admin" && inputPw == "qwer") {

                Toast.makeText(this, "관리자님 환영합니다.", Toast.LENGTH_SHORT).show()

                getUserInfo()

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

    fun getUserInfo (){

        val myIntent = Intent(this,MainActivity::class.java)
        startActivity(myIntent)

        Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()

    }
}