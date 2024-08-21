package com.example.e_book.data_layer.network.repo

import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import com.example.e_book.domain_layer.repo.AllBookRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl @Inject constructor(val firebaseDatabase: FirebaseDatabase): AllBookRepo {
    override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookModel> = emptyList()
                snapshot.children.map {value->
                    val book: BookModel = value.getValue(BookModel::class.java)!!
                }
                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }

        
    }
}