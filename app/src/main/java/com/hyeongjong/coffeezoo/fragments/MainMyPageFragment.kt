package com.hyeongjong.coffeezoo.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.databinding.FragmentMainMyPageBinding
import com.hyeongjong.coffeezoo.datas.ProfileData
import com.hyeongjong.coffeezoo.utils.ContextUtil

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
        getProfile()

    }

    override fun setupEvents() {

        binding.btnPhoneNumSave.setOnClickListener {

            val edtPhoneNum = binding.edtPhoneNum.text.toString() //edtPhoneNum에 입력된 text

            if ( edtPhoneNum == null ) {
                Toast.makeText(mContext, "휴대폰번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else{
                binding.edtPhoneNum.setHint(edtPhoneNum) //edtPhoneNum에 입력된 text를 hint로 바꾸기
            }

            binding.edtPhoneNum.text.clear() //edtPhoneNum에 입력된 text 지우기
            binding.edtPhoneNum.clearFocus()

        }

    }

    override fun setValues() {

    }

    fun getProfile() {

        val sharedId = ContextUtil.getLoginId(mContext)
        val database = Firebase.database("https://coffeezoo-30c55-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val query = database.getReference("ProfileData").orderByChild("id").equalTo(sharedId)

        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for ( userSnapshot in snapshot.children ) {

                    val getData = userSnapshot.getValue(ProfileData::class.java)

                    Log.d(TAG, "getDataKey: "+userSnapshot.key)

                    binding.txtEmail.text = getData?.id
                    binding.txtNickname.text = getData?.nick
                    binding.edtPhoneNum.setHint(getData?.phoneNumber)
                    binding.switchMail.isChecked = getData!!.marketingMail
                    binding.switchSms.isChecked = getData!!.marketingSms
                    Glide.with(mContext).load(getData?.profileImage).into(binding.imgProfile)

                    Log.d(TAG, "Log txtEmail: "+getData?.id)
                    Log.d(TAG, "Log txtNickname: "+getData?.nick)
                    Log.d(TAG, "Log edtPhoneNum: "+getData?.phoneNumber)
                    Log.d(TAG, "Log switchMail: "+getData?.marketingMail)
                    Log.d(TAG, "Log switchSms: "+getData?.marketingSms)
                    Log.d(TAG, "Log profileImage: "+getData?.profileImage)
                    Log.d(TAG, "Log num: "+getData?.num)

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return

    }

}