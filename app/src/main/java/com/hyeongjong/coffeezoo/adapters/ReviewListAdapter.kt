package com.hyeongjong.coffeezoo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.ReviewData

data class ReviewListAdapter(
    val mContext : Context,
) : RecyclerView.Adapter<ReviewListAdapter.MyViewHolder>() {

    var mList = mutableListOf<ReviewData>()

    fun setListData(data:MutableList<ReviewData>){
        mList = data
    }

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        val imgPhotoListPhoto = view.findViewById<ImageView>(R.id.imgPhotoListPhoto) //카페사진
        val txtPhotoListComment = view.findViewById<TextView>(R.id.txtPhotoListComment) //카페후기

        fun bind(data : ReviewData){

            Glide.with(mContext).load(data.image).into(imgPhotoListPhoto) //카페사진
            txtPhotoListComment.text = data.comment //카페후기
            imgPhotoListPhoto.clipToOutline = true

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.review_list_item, parent, false) //item연결
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        실제 출력할 데이터

        val data = mList[position]

//        MyViewHolder도 일종의 클래스 : 멤버변수 / 함수를 가지고 있을 수 있다. => 활용하자.
        holder.bind( data )

    }

    override fun getItemCount() = mList.size // 목록의 갯수가 리턴.
}