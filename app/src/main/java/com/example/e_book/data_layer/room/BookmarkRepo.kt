package com.example.e_book.data_layer.room

import javax.inject.Inject

class BookmarkRepo @Inject constructor (private val bookmarkDao: BookmarkDao){

    suspend fun addBookmark(bookmark: BookmarkModel){
        bookmarkDao.addBookmark(bookmark)
    }

    suspend fun deleteBookmark(bookmark: BookmarkModel){
        bookmarkDao.deleteBookmark(bookmark)
    }

    suspend fun getAllBookmark(): List<BookmarkModel>{
        return bookmarkDao.getAllBookmark()
    }
}