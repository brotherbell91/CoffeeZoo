package com.hyeongjong.coffeezoo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hyeongjong.coffeezoo.R
import com.hyeongjong.coffeezoo.app.OnCafeClickListener
import com.hyeongjong.coffeezoo.app.OnSearchClickListener
import com.hyeongjong.coffeezoo.datas.SearchData


data class SearchListAdapter(var searchList : ArrayList<SearchData>) : RecyclerView.Adapter<SearchListAdapter.CustomViewHolder>() {

    private var listener: OnSearchClickListener? = null

    fun setListener(onSearchClickListener: OnSearchClickListener) {
        this.listener = onSearchClickListener
    }

//  뷰홀더가 메모리에 올라갔을 때 뷰홀더와 레이아웃을 연결 시켜준다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return CustomViewHolder(view)

    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchListAdapter.CustomViewHolder, position: Int) {
        holder.txtSearchText.text = searchList.get(position).search
        holder.txtSearchDate.text = searchList.get(position).date

//        Custom OnClickListener Event
        holder.itemView.setOnClickListener(View.OnClickListener {
            if (listener != null) {
                listener!!.onSearchClickEvent(searchList[position])
            }
        })
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val txtSearchText = itemView.findViewById<TextView>(R.id.txtSearchText) //검색어
        val txtSearchDate = itemView.findViewById<TextView>(R.id.txtSearchDate) //검색날짜

    }

}