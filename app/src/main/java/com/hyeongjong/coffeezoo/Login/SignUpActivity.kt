package com.hyeongjong.coffeezoo.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import bolts.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hyeongjong.coffeezoo.BaseActivity
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        setupEvents()
        setValues()


    }

    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {

            signUp()

        }

        binding.btnSignUpCancel.setOnClickListener {

            finish()

        }

    }

    override fun setValues() {

    }

    fun signUp() {

        var edtInputNewId = binding.edtInputNewId.text.toString()
        var edtInputNewPw = binding.edtInputNewPw.text.toString()
        var edtInputNewPw2 = binding.edtInputNewPw2.text.toString()

        if (edtInputNewPw == edtInputNewPw2) {

            auth?.createUserWithEmailAndPassword(edtInputNewId, edtInputNewPw)?.addOnCompleteListener {

                    task ->
                if(task.isSuccessful){
                    //정상적으로 이메일과 비번이 전달되어
                    //새 유저 계정을 생성과 서버db 저장 완료
                    //즉, 기존에 있는 계정이 아니다!
                    goLoginMainActivity(task.result?.user)
                    Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()
                }
                else if (task.exception?.message.isNullOrEmpty()==false){
                    //예외 혹은 에러 메시지
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                }
                else {
                    //이미 있는 계정
                    Toast.makeText(this, "이미 등록된 계정입니다", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else {

            Toast.makeText(this, "비밀번호가 맞지 않습니다", Toast.LENGTH_SHORT).show()

        }

    }

    // LoginMainActivity로 이동
    fun goLoginMainActivity(user: FirebaseUser?) {
        // 파이어베이스 유저 상태가 생성된 경우 다음 페이지로 넘어갈 수 있음
        if(user != null) {

            startActivity(Intent(this, LoginMainActivity::class.java))

        }
    }


}