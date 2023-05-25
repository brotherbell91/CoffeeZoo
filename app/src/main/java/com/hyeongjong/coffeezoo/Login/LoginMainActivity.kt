package com.hyeongjong.coffeezoo.Login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hyeongjong.coffeezoo.BaseActivity
import com.hyeongjong.coffeezoo.MainActivity
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.databinding.ActivityLoginMainBinding
import com.hyeongjong.coffeezoo.utils.ContextUtil
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import org.json.JSONObject
import java.util.*

class LoginMainActivity : BaseActivity() {

    lateinit var binding : ActivityLoginMainBinding

    lateinit var mCallbackManager : CallbackManager //페북 로그인 화면에 다녀오면, 할 일을 관리해주는 변수.

    var auth: FirebaseAuth? = null;

    private var email : String = ""
    private var profileImage : String = ""
    private var nick : String = ""
    private var phoneNumber : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_main)

        auth = FirebaseAuth.getInstance()
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

//        회원가입 클릭 이벤트
        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(this,SignUpActivity::class.java)
            startActivity(myIntent)

        }

//        페북 로고가 클릭시 > 페이스북 로그인
        binding.imgFacebook.setOnClickListener {

//            페북로그인 기능에 관련된 코드 준비가 필요함. (준비 먼저 하고 로그인 실행 : mCallbackManager 세팅)
//            1. 로그인 화면에 다녀오면 어떤 행동을 할지? 할 일 설정.
            LoginManager.getInstance().registerCallback(mCallbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {

//                    페북 로그인 성공 > 페북 서버의 액세스 토큰값 내려줌.

//                    받은 토큰으로, 내정보도 받기 => GraphRequest 클래스 활용

//                    1. 내 정보를 받아서 할일을 계획

                    val graphRequest = GraphRequest.newMeRequest(result!!.accessToken, object : GraphRequest.GraphJSONObjectCallback{
                        override fun onCompleted(jsonObj: JSONObject?, response: GraphResponse?) {

                            Log.d("페북로그인-내정보", jsonObj!!.toString())

//                            받은 정보에서 id값, 이름 추출(서버에 저장해야 활용 가능)
                            email = jsonObj.getString("id")
                            nick = jsonObj.getString("name")
//                            profileImage
//                            phoneNumber


                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)
                            finish()

                        }

                    })

//                    2. 실제 내 정보 받아오기 실행

                    graphRequest.executeAsync()

                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {

                }
            })
//            2. 실제 로그인 실행

//            이 화면에서, 공개프로필/이메일 권한 (예시)
            LoginManager.getInstance().logInWithReadPermissions(this,
                Arrays.asList("public_profile", "email"))

        }

//        카카오 로고 클릭시 > 카카오 로그인
        binding.imgKakao.setOnClickListener {
//            카톡 앱이 깔려있으면? 앱으로 로그인, 아니면? 별도로 로그인
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)){
                //카톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->
                    if (error != null) {
                        //뒤로가기시
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            Toast.makeText(mContext, "카카오톡으로 로그인 실패", Toast.LENGTH_SHORT).show()
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(mContext) { token, error ->
                            if (error != null) {
                                Toast.makeText(this, "카카오계정으로 로그인 실패", Toast.LENGTH_SHORT).show()
                            }
                            else if (token != null) {//로그인 성공
                                //메인페이지 이동
                                val myIntent = Intent(this,MainActivity::class.java)
                                startActivity(myIntent)
                                Toast.makeText(this, "카카오계정으로 로그인 성공", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        //카톡 앱에서 정보 가져오기
                        UserApiClient.instance.me { user, error ->
                            if (user != null) {

                                val email = user.kakaoAccount?.email.toString()
                                val nick = user.kakaoAccount?.profile?.nickname.toString()
                                val profileImage = user.kakaoAccount?.profile?.thumbnailImageUrl.toString()
                                val phoneNumber = user.kakaoAccount?.phoneNumber.toString()

                                Log.i("사용자 정보", "사용자 정보 요청 성공" +
                                        "\n이메일: ${email}" +
                                        "\n닉네임: ${nick}" +
                                        "\n프로필사진: ${profileImage}" +
                                        "\n휴대폰번호: ${phoneNumber}")

                                //메인페이지 이동
                                val myIntent = Intent(this,MainActivity::class.java)
                                startActivity(myIntent)
                                Toast.makeText(this, "카카오톡으로 로그인 성공", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else{//카카오 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(mContext) { token, error ->
                    if (error != null) {
                        Toast.makeText(this, "카카오계정으로 로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                    else if (token != null) {//로그인 성공
                        //메인페이지 이동
                        val myIntent = Intent(this,MainActivity::class.java)
                        startActivity(myIntent)
                        Toast.makeText(this, "카카오계정으로 로그인 성공", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

//        네이버 로고 클릭시 > 네이버 로그인
        binding.imgNaver.setOnClickListener {
            val oauthLoginCallback = object : OAuthLoginCallback {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                override fun onSuccess() {
                    // 네이버 로그인 API 호출 성공 시 유저 정보를 가져온다
                    NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                        override fun onSuccess(result: NidProfileResponse) {
                            email = result.profile?.email.toString()
                            profileImage = result.profile?.profileImage.toString()
                            nick = result.profile?.nickname.toString()
                            phoneNumber = result.profile?.mobile.toString()

                            Log.e(TAG, "네이버 로그인한 유저 정보 - 이메일 : $email")
                            Log.e(TAG, "네이버 로그인한 유저 정보 - 프로필이미지 : $profileImage")
                            Log.e(TAG, "네이버 로그인한 유저 정보 - 닉네임 : $nick")
                            Log.e(TAG, "네이버 로그인한 유저 정보 - 휴대폰번호 : $phoneNumber")
                        }

                        override fun onError(errorCode: Int, message: String) {
                            //
                        }

                        override fun onFailure(httpStatus: Int, message: String) {
                            //
                        }
                    })

                    val myIntent = Intent(mContext, MainActivity::class.java)
                    startActivity(myIntent)
                    finish()

                }
                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    Toast.makeText(mContext,"errorCode:$errorCode, errorDesc:$errorDescription",Toast.LENGTH_SHORT).show()
                }
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            }

            NaverIdLoginSDK.initialize(mContext, getString(R.string.naver_client_id), getString(R.string.naver_client_secret), "CoffeeZoo")
            NaverIdLoginSDK.authenticate(mContext, oauthLoginCallback)

        }

//        로그인 버튼 구현
        binding.btnLogin.setOnClickListener {
            var inputId = binding.edtInputId.text.toString()
            var inputPw = binding.edtInputPw.text.toString()
            Log.d(TAG, "@@@@ setupEvents: "+ inputId + ""+ inputPw)
            if(inputId == "") {
                if (inputPw == "") {
                    Toast.makeText(mContext, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(mContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }else if (inputPw == "") {
                if (inputId == "") {
                    Toast.makeText(mContext, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(mContext, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }else{
                signinEmail(inputId, inputPw)
            }
        }

    }

    override fun setValues() {

        mCallbackManager = CallbackManager.Factory.create() // 페북로그인 - 콜백 관리 기능 초기화.

    }

//    페북로그인 화면에 다녀오면, 콜백매니저가 처리하도록.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

//    이메일 로그인
    fun signinEmail(id : String, pw : String) {

        ContextUtil.setLoginId(this, id)

    auth?.signInWithEmailAndPassword(id,pw)
        ?.addOnCompleteListener {
                task ->
            if(task.isSuccessful) {
                // Login, 아이디와 패스워드가 맞았을 때
                moveMainPage(task.result?.user)
                Toast.makeText(this,id+"님 환영합니다", Toast.LENGTH_LONG).show()
            }else{
                // Show the error message, 아이디와 패스워드가 틀렸을 때
                Toast.makeText(this,"아이디와 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
            }
        }

    }

//    파이어베이스 유저 확인 후 로그인
    fun moveMainPage(user: FirebaseUser?) {
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun getNaverUserInfo() {

//        val database = Firebase.database("https://coffeezoo-30c55-default-rtdb.asia-southeast1.firebasedatabase.app/")
//        val myRef = database.getReference("")


    }
}