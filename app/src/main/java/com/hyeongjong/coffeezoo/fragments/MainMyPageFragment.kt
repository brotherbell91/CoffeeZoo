package com.hyeongjong.coffeezoo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.databinding.FragmentMainMyPageBinding
import com.kakao.sdk.user.UserApiClient

class MainMyPageFragment : BaseFragment() {

    lateinit var binding : FragmentMainMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_my_page,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.btnPhoneNumSave.setOnClickListener {

            val edtPhoneNum1 = binding.edtPhoneNum1.text.toString() //edtPhoneNum1에 입력된 text
            val edtPhoneNum2 = binding.edtPhoneNum2.text.toString()
            val edtPhoneNum3 = binding.edtPhoneNum3.text.toString()

            if ( edtPhoneNum1 == "" || edtPhoneNum2 == "" || edtPhoneNum3 == "" ) {
                Toast.makeText(mContext, "휴대폰번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else{
                binding.edtPhoneNum1.setHint(edtPhoneNum1) //edtPhoneNum1에 입력된 text를 hint로 바꾸기
                binding.edtPhoneNum2.setHint(edtPhoneNum2)
                binding.edtPhoneNum3.setHint(edtPhoneNum3)
            }

            binding.edtPhoneNum1.text.clear() //edtPhoneNum1에 입력된 text 지우기
            binding.edtPhoneNum2.text.clear()
            binding.edtPhoneNum3.text.clear()

            binding.edtPhoneNum1.clearFocus()
            binding.edtPhoneNum2.clearFocus()
            binding.edtPhoneNum3.clearFocus()

        }

    }

    override fun setValues() {

//        사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("사용자 정보", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {

                val userId = user.id
                val userEmail = user.kakaoAccount?.email
                val userNick = user.kakaoAccount?.profile?.nickname
                val userProfile = user.kakaoAccount?.profile?.thumbnailImageUrl

                Log.i("사용자 정보", "사용자 정보 요청 성공" +
                        "\n회원번호: ${userId}" +
                        "\n이메일: ${userEmail}" +
                        "\n닉네임: ${userNick}" +
                        "\n프로필사진: ${userProfile}")

                binding.txtNickname.text = userNick
                binding.txtEmail.text = userEmail
                Glide.with(mContext).load(userProfile).into(binding.imgProfile) //카페로고

            }
        }




    }
}