package com.example.playwithcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithcompose.data.networking.ApiResult
import com.example.playwithcompose.data.networking.model.Todo
import com.example.playwithcompose.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val todoState = MutableStateFlow<ApiResult<Todo>>(ApiResult.Progress)

    fun getTodo(id: Long) {
        viewModelScope.launch {
            val result = repository.getTodo(id)
            todoState.value = result
        }
    }
}
