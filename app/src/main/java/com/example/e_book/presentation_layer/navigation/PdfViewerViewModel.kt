package com.example.e_book.presentation_layer.navigation

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
class PdfViewerViewModel @Inject constructor(private val repo: BookmarkRepo): ViewModel() {
    private val _bookmarks = MutableStateFlow<List<BookmarkModel>>(emptyList())
    val bookmarks : StateFlow<List<BookmarkModel>> = _bookmarks.asStateFlow()

    fun addBookmark(bookmark: BookmarkModel){
        viewModelScope.launch {
            repo.addBookmark(bookmark)
        }
    }

    fun getAllBookmark(){
        viewModelScope.launch {
            _bookmarks.value = repo.getAllBookmark()
        }
    }

}