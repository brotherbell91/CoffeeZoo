package com.hyeongjong.coffeezoo.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.ReviewData

class Repo {

    fun getCafeData() : LiveData<MutableList<CafeData>> {

        val mutableData = MutableLiveData<MutableList<CafeData>>()
        val database = Firebase.database("https://coffeezoo-30c55-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val myRef = database.getReference("CafeData")

        myRef.addValueEventListener(object : ValueEventListener {

            val listData : MutableList<CafeData> = mutableListOf<CafeData>()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    listData.clear() //실시간 데이터 업데이트 시 리사이클러뷰 데이터 중복 방지

                    for ( userSnapshot in snapshot.children ){

                        val getData = userSnapshot.getValue(CafeData::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData

                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {



            }


        })
        return mutableData

    }

    fun getReviewData() : LiveData<MutableList<ReviewData>> {

        val mutableData = MutableLiveData<MutableList<ReviewData>>()
        val database = Firebase.database("https://coffeezoo-30c55-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val myRef = database.getReference("ReviewData")

        myRef.addValueEventListener(object : ValueEventListener {

            val listData : MutableList<ReviewData> = mutableListOf<ReviewData>()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    listData.clear() //실시간 데이터 업데이트 시 리사이클러뷰 데이터 중복 방지

                    for ( userSnapshot in snapshot.children ){

                        val getData = userSnapshot.getValue(ReviewData::class.java)
                        if (getData!!.image != "image") {

                            listData.add(getData!!)

                            mutableData.value = listData

                        }

                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {



            }


        })
        return mutableData

    }

    fun getCommentData(cafeName : String) : LiveData<MutableList<ReviewData>> {

        val mutableData = MutableLiveData<MutableList<ReviewData>>()
        val database = Firebase.database("https://coffeezoo-30c55-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val myRef = database.getReference("ReviewData").orderByChild("cafeName").equalTo(cafeName)

        myRef.addValueEventListener(object : ValueEventListener {

            val listData : MutableList<ReviewData> = mutableListOf<ReviewData>()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    listData.clear() //실시간 데이터 업데이트 시 리사이클러뷰 데이터 중복 방지

                    for ( userSnapshot in snapshot.children ){

                        val getData = userSnapshot.getValue(ReviewData::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData

                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {



            }


        })
        return mutableData

    }

}