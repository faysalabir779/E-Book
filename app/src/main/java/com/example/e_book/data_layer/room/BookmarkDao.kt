package com.example.e_book.data_layer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkModel)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkModel)

    @Query("SELECT * FROM bookmarks_table ORDER BY id DESC")
    suspend fun getAllBookmark() : List<BookmarkModel>
}