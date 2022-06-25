package com.hyeongjong.coffeezoo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.app.OnItemClick
import com.hyeongjong.coffeezoo.datas.SearchData


data class SearchListAdapter(
    var mContext : Context,
    val mList : List<SearchData>,
) : RecyclerView.Adapter<SearchListAdapter.MyViewHolder>() {

    var oic : OnItemClick? = null

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val txtSearchText = view.findViewById<TextView>(R.id.txtSearchText) //검색어
        val txtSearchDate = view.findViewById<TextView>(R.id.txtSearchDate) //검색날짜
        val btnSearchDelete = view.findViewById<ImageView>(R.id.btnSearchDelete) //검색어삭제버튼

        fun bind(data : SearchData) {

            txtSearchText.text = data.search
            txtSearchDate.text = data.date

            if (oic != null) {
                btnSearchDelete.setOnClickListener {
                    oic!!.onItemClick(position)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, parent, false)
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