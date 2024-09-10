package com.example.e_book.data_layer.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookmarkModel::class], version = 1)
abstract class BookmarkDataBase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}