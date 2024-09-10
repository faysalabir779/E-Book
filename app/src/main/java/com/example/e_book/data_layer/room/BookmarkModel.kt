package com.example.e_book.data_layer.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks_table")
data class BookmarkModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bookName: String,
    val bookAuthor: String,
    val bookImage: String,
    val bookUrl: String,
    val bookPage: Int

)