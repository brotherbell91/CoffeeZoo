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
import com.hyeongjong.coffeezoo.datas.ReviewData

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
        val imgCafeDetailPhoto = view.findViewById<ImageView>(R.id.imgCafeDetailPhoto) //사진

        fun bind(data : ReviewData){

            txtCafeDetailNick.text = data.nick //닉네임
            txtCafeDetailComment.text = data.comment

            if (oic != null) {
                imgCafeDetailPhoto.setOnClickListener {
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