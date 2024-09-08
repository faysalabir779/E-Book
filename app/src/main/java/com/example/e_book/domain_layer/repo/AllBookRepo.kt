package com.example.e_book.domain_layer.repo

import com.example.e_book.common.BookCategoryModel
import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {
    fun getAllBooks() : Flow<ResultState<List<BookModel>>>
    fun getAllCategory() : Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>>
}