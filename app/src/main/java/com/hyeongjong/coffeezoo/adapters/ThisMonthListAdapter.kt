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
import com.hyeongjong.coffeezoo.app.OnItemClick
import com.hyeongjong.coffeezoo.app.OnItemLongClick
import com.hyeongjong.coffeezoo.datas.CafeData
import com.willy.ratingbar.BaseRatingBar

data class ThisMonthListAdapter(
    val mContext : Context,
) : RecyclerView.Adapter<ThisMonthListAdapter.MyViewHolder>() {

    var mList = mutableListOf<CafeData>()

    var oic : OnItemClick? = null
    var oilc : OnItemLongClick? = null

    fun setListData(data:MutableList<CafeData>){
        mList = data
    }

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        val cafeName = view.findViewById<TextView>(R.id.txtItemCafeName) //카페이름
        val cafeAddress = view.findViewById<TextView>(R.id.txtItemCafeAddress) //카페주소
        val cafeDescription = view.findViewById<TextView>(R.id.txtItemCafeDescription) //카페설명
        val ratingBarCafeScore = view.findViewById<BaseRatingBar>(R.id.ratingBarCafeScore) //카페별점
        val imgCafeLogo = view.findViewById<ImageView>(R.id.imgCafeLogo) //카페로고

        fun bind(data : CafeData){

            cafeName.text = data.cafeName //카페이름
            cafeAddress.text = data.cafeAddress //카페주소
            cafeDescription.text = data.cafeDescription //카페설명
            ratingBarCafeScore.rating = data.score.toFloat() //카페별점
            Glide.with(mContext).load(data.logoUrl).into(imgCafeLogo) //카페로고

            if (oic != null) {
                view.setOnClickListener {
                    oic!!.onItemClick(position)
                }
            }

            if (oilc != null) {
                view.setOnLongClickListener {
                    oilc!!.onItemLongClick(position)
                    return@setOnLongClickListener true
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.this_month_list_item, parent, false)
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