package com.example.e_book.data_layer.network.repo

import android.util.Log
import com.example.e_book.common.BookCategoryModel
import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import com.example.e_book.common.SubCategory
import com.example.e_book.domain_layer.repo.AllBookRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl @Inject constructor(val firebaseDatabase: FirebaseDatabase) : AllBookRepo {
    override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookModel> = emptyList()
                items = snapshot.children.map { value ->
                    value.getValue<BookModel>()!!
                }
                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }

        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.child("Books").removeEventListener(valueEvent)
            close()
        }


    }

    override fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var category: List<BookCategoryModel> = emptyList()
                category = snapshot.children.map {
                    it.getValue<BookCategoryModel>()!!
                }
                trySend(ResultState.Success(category))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("BooksCategory").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.child("BooksCategory").removeEventListener(valueEvent)
            close()
        }
    }

    override fun getAllBooksByCategory(Category: String, subCategory: String): Flow<ResultState<List<BookModel>>> =
        callbackFlow {
            trySend(ResultState.Loading)

            val valueEvent = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull {
                        val book = it.getValue<BookModel>()
                        if (book?.category == Category && book.subCategory == subCategory) {
                            book
                        }else{
                            null
                        }
                    }
                    trySend(ResultState.Success(items))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResultState.Error(error.toException()))
                }
            }

            firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
            awaitClose {
                firebaseDatabase.reference.child("Books").removeEventListener(valueEvent)
                close()
            }
        }

    override fun getAllSubCategory(categoryName: String): Flow<ResultState<List<SubCategory>>> = callbackFlow {
        trySend(ResultState.Loading)
            val valueEvent = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var subCategoryList: List<SubCategory> = emptyList()


                    snapshot.children.forEach{ it ->
                        val bookCategory = it.getValue(BookCategoryModel::class.java)

                        if (bookCategory?.name == categoryName){
                            val subCategoriesSnapshot = it.child("subCategory")

                            subCategoryList = subCategoriesSnapshot.children.mapNotNull {
                                it.getValue(SubCategory::class.java)
                            }
                        }
                    }
                    // Send the list of subcategories to the flow
                    trySend(ResultState.Success(subCategoryList))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResultState.Error(error.toException()))
                }
            }

            // Query the category node
        firebaseDatabase.reference.child("BooksCategory")
            .addValueEventListener(valueEvent) // Use addValueEventListener

        awaitClose {
            firebaseDatabase.reference.child("BooksCategory")
                .removeEventListener(valueEvent) // Remove the listener when the flow is closed
            close()
        }
        }

}


