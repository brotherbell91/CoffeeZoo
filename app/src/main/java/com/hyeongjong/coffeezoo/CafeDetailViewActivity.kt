package com.hyeongjong.coffeezoo

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hyeongjong.coffeezoo.adapters.CafeDetailViewAdapter
import com.hyeongjong.coffeezoo.app.OnItemClick
import com.hyeongjong.coffeezoo.databinding.ActivityCafeDetailViewBinding
import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.lifecycle.ListViewModel

class CafeDetailViewActivity : BaseActivity() {

    lateinit var binding: ActivityCafeDetailViewBinding

    lateinit var mCafeData : CafeData

    lateinit var mAdapter : CafeDetailViewAdapter

    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cafe_detail_view)

        setupEvents()
        setValues()
        observerData()

    }

    override fun setupEvents() {
        //        백버튼 누르면 화면 종료
        binding.imgBack.setOnClickListener {
            finish()
        }

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
        Glide.with(mContext).load(mCafeData.logoUrl).into(binding.imgCafe) //임시로 카페로고사용, 서버에서 가져올 카페사진 필요

        //어답터 연결
        mAdapter = CafeDetailViewAdapter(mContext)
        //리싸이클러뷰 연결
        binding.DetailCafeCommentRecyclerView.adapter = mAdapter
        //리싸이클러뷰 모양설정
        binding.DetailCafeCommentRecyclerView.layoutManager = LinearLayoutManager(mContext)


    }

    fun observerData(){
//        fragment 에서 this 대신 viewLifecycleOwner 를 사용
        viewModel.fetchCommentData(mCafeData.cafeName).observe(this, Observer {
            mAdapter.setListData(it)
            mAdapter.notifyDataSetChanged()

//            클릭이벤트
//            mAdapter.oic = object : OnItemClick {
//                override fun onItemClick(position: Int) {
//
//                    val clickCafeStore = it[position]
//
//                    val myIntent = Intent(mContext, CafeDetailViewActivity::class.java)
//                    myIntent.putExtra("clickedCafeDetail", clickCafeStore)
//                    startActivity(myIntent)
//
//                }
//
//            }

        })

    }
}