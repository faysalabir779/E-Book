package com.example.e_book.presentation_layer.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.common.BookCategoryModel
import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import com.example.e_book.common.SubCategory
import com.example.e_book.domain_layer.repo.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ViewModel @Inject constructor(val repo: AllBookRepo) : ViewModel() {
    private val _state:MutableState<ItemsState> = mutableStateOf(ItemsState())
    val state: MutableState<ItemsState> = _state

    fun loadBooks(){
        viewModelScope.launch {
            repo.getAllBooks().collect{
                when(it){
                    is ResultState.Error -> _state.value = ItemsState(error = it.exception.localizedMessage)
                    ResultState.Loading -> _state.value = ItemsState(isLoading = true)
                    is ResultState.Success -> _state.value = ItemsState(books = it.data)
                }
            }
        }
    }
    fun loadCategory(){
        viewModelScope.launch {
            repo.getAllCategory().collect{
                when(it){
                    is ResultState.Error -> _state.value = ItemsState(error = it.exception.localizedMessage)
                    ResultState.Loading -> _state.value = ItemsState(isLoading = true)
                    is ResultState.Success -> _state.value = ItemsState(category = it.data)
                }
            }
        }
    }

    fun loadBooksByCategory(category: String) {
        viewModelScope.launch {
            repo.getAllBooksByCategory(category).collect{
                when(it){
                    is ResultState.Error -> _state.value = ItemsState(error = it.exception.localizedMessage)
                    ResultState.Loading -> _state.value = ItemsState(isLoading = true)
                    is ResultState.Success -> _state.value = ItemsState(books = it.data)
                }
            }
        }
    }

    fun loadSubCategory(categoryName: String){
        viewModelScope.launch {
            repo.getAllSubCategory(categoryName).collect{
                when(it){
                    is ResultState.Error -> _state.value = ItemsState(error = it.exception.localizedMessage)
                    ResultState.Loading -> _state.value = ItemsState(isLoading = true)
                    is ResultState.Success -> _state.value = ItemsState(subCategory = it.data)
                }
            }
        }
    }

}

data class ItemsState(
    val books: List<BookModel> = emptyList(),
    val category: List<BookCategoryModel> = emptyList(),
    val subCategory: List<SubCategory> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)