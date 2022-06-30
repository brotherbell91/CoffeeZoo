package com.hyeongjong.coffeezoo

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hyeongjong.coffeezoo.databinding.ActivityCafeDetailViewBinding
import com.hyeongjong.coffeezoo.datas.CafeData


class CafeDetailViewActivity : BaseActivity() {

    lateinit var binding: ActivityCafeDetailViewBinding

    lateinit var mCafeData : CafeData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cafe_detail_view)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.txtDetailCafeNumber.setOnClickListener {

            val pl = object : PermissionListener {
                override fun onPermissionGranted() {

                    val myUri = Uri.parse("tel:${mCafeData.cafeNumber}")
                    val myIntent = Intent(Intent.ACTION_CALL,myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                    Toast.makeText(this@CafeDetailViewActivity,
                        "전화 연결을 하려면, 권한이 필요합니다.",
                        Toast.LENGTH_SHORT).show()
                }

            }

            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()

        }

        binding.insta.setOnClickListener {

            val myUri = Uri.parse("${mCafeData.cafeInsta}")
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)

            startActivity(myIntent)

        }

        binding.txtDetailCafeAddress.setOnClickListener {

            val myIntent = Intent(this, CafeMapActivity::class.java)

            myIntent.putExtra("mapView",mCafeData)

            startActivity(myIntent)

        }

    }

    override fun setValues() {

        mCafeData = intent.getSerializableExtra("clickedCafeDetail") as CafeData

        binding.txtDetailCafeName.text = mCafeData.cafeName
        binding.txtDetailCafeAddress.text = mCafeData.cafeAddress
        binding.txtDetailCafeNumber.text = mCafeData.cafeNumber
        binding.txtDetailCafeDescription.text = mCafeData.cafeDescription
        binding.ratingBarDetailCafeScore.rating = mCafeData.score.toFloat()

    }
}