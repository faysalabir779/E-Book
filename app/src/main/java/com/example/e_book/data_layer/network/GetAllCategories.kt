package com.example.e_book.data_layer.network

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import javax.inject.Inject

class GetAllCategories @Inject constructor(val firebaseDatabase: FirebaseDatabase) {
    fun getAllCategory(){
        val categoryListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

            }
            override fun onCancelled(error: DatabaseError) {}
        }
        firebaseDatabase.reference.child("BookCategory").addValueEventListener(categoryListener)
    }
}