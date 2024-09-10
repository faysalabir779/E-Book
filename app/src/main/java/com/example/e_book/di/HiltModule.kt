package com.example.e_book.di

import android.content.Context
import androidx.room.Room
import com.example.e_book.data_layer.network.repo.AllBookRepoImpl
import com.example.e_book.data_layer.room.BookmarkDataBase
import com.example.e_book.domain_layer.repo.AllBookRepo
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun provideFirebaseRealtimeDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepo(firebaseDatabase: FirebaseDatabase): AllBookRepo{
        return AllBookRepoImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookmarkDataBase {
        return Room.databaseBuilder(context, BookmarkDataBase::class.java, "EBook_Database").build()
    }

    @Provides
    fun provideBookmarkDao(database: BookmarkDataBase) = database.bookmarkDao()

}