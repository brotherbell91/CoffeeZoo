package com.hyeongjong.coffeezoo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.app.OnItemClick
import com.hyeongjong.coffeezoo.datas.ReviewData
import com.willy.ratingbar.BaseRatingBar

data class CafeDetailViewAdapter(
    val mContext : Context,
) : RecyclerView.Adapter<CafeDetailViewAdapter.MyViewHolder>() {

    var mList = mutableListOf<ReviewData>()

    var oic : OnItemClick? = null

    fun setListData(data:MutableList<ReviewData>){
        mList = data
    }

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        val txtCafeDetailNick = view.findViewById<TextView>(R.id.txtCafeDetailNick) //닉네임
        val txtCafeDetailComment = view.findViewById<TextView>(R.id.txtCafeDetailComment) //댓글
        val imgCafeDetailImage = view.findViewById<ImageView>(R.id.imgCafeDetailImage) //후기이미지
        val imgCafeDetailCamera = view.findViewById<ImageView>(R.id.imgCafeDetailCamera) //카메라
        val txtCafeDetailDate = view.findViewById<TextView>(R.id.txtCafeDetailDate) //날짜
        val ratingBarCafeDetailScore = view.findViewById<BaseRatingBar>(R.id.ratingBarCafeDetailScore) //댓글별점


        fun bind(data : ReviewData){

            txtCafeDetailNick.text = data.nick //닉네임
            txtCafeDetailComment.text = data.comment //댓글
            Glide.with(mContext).load(data.image).into(imgCafeDetailImage) //후기이미지
            txtCafeDetailDate.text = data.date //날짜
            ratingBarCafeDetailScore.rating = data.score.toFloat() //댓글별점

            imgCafeDetailImage.clipToOutline = true //이미지에 라운드 넣기

            if (data.image == "image"){ //파이어베이스에 이미지가 없다면 카메라아이콘 제거와 이미지자리 없애기
                imgCafeDetailCamera.isGone = true
                imgCafeDetailImage.isGone = true
            }


            if (oic != null) {
                imgCafeDetailImage.setOnClickListener {
                    oic!!.onItemClick(position)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.cafe_detail_view_list_item, parent, false)
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