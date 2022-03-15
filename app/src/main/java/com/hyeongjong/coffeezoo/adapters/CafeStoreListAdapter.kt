package com.hyeongjong.coffeezoo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.app.OnClickListener
import com.hyeongjong.coffeezoo.datas.CafeData
import com.willy.ratingbar.BaseRatingBar


data class CafeStoreListAdapter(var cafeList : ArrayList<CafeData>) : RecyclerView.Adapter<CafeStoreListAdapter.CustomViewHolder>() {

    private var listener: OnClickListener? = null

    fun setListener(onClickListener: OnClickListener) {
        this.listener = onClickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeStoreListAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cafe_list_item, parent, false)
        return CustomViewHolder(view)

        }


    override fun getItemCount(): Int {
        return cafeList.size
    }

    override fun onBindViewHolder(holder: CafeStoreListAdapter.CustomViewHolder, position: Int) {
        holder.cafeName.text = cafeList.get(position).cafeName
        holder.cafeAddress.text = cafeList.get(position).cafeAddress
        holder.cafeDescription.text = cafeList.get(position).cafeDescription
        holder.ratingBarCafeScore.rating = cafeList.get(position).score.toFloat()

//        Custom OnClickListener Event
        holder.itemView.setOnClickListener(View.OnClickListener {
            if (listener != null) {
                listener!!.onClickEvent(cafeList[position])
            }
        })
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val cafeName = itemView.findViewById<TextView>(R.id.txtItemCafeName) //카페이름
            val cafeAddress = itemView.findViewById<TextView>(R.id.txtItemCafeAddress) //카페주소
            val cafeDescription = itemView.findViewById<TextView>(R.id.txtItemCafeDescription) //카페설명
            val ratingBarCafeScore = itemView.findViewById<BaseRatingBar>(R.id.ratingBarCafeScore) //카페별점
    }

    fun filterList(filteredNames: ArrayList<CafeData>) {
        this.cafeList = filteredNames
        notifyDataSetChanged()
    }

}