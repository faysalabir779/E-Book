package com.example.e_book.presentation_layer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.data_layer.room.BookmarkModel
import com.example.e_book.data_layer.room.BookmarkRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val bookmarkRepo: BookmarkRepo):ViewModel() {
    private val _bookmarks = MutableStateFlow<List<BookmarkModel>>(emptyList())
    val bookmarks: StateFlow<List<BookmarkModel>> = _bookmarks.asStateFlow()

    init {
        loadAllBook()
    }

    private fun loadAllBook(){
        viewModelScope.launch {
            _bookmarks.value = bookmarkRepo.getAllBookmark()
        }
    }

    fun deleteBook(bookmark: BookmarkModel){
        viewModelScope.launch {
            bookmarkRepo.deleteBookmark(bookmark)
            loadAllBook()
        }
    }
}