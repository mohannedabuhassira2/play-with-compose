package com.example.playwithcompose.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithcompose.data.networking.ApiResult
import com.example.playwithcompose.data.networking.model.Todo
import com.example.playwithcompose.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _singleTodo = mutableStateOf<ApiResult<Todo>>(ApiResult.Progress)
    val singleTodo: State<ApiResult<Todo>> = _singleTodo

    private val _todos = mutableStateOf<ApiResult<List<Todo>>>(ApiResult.Progress)
    val todos: State<ApiResult<List<Todo>>> = _todos

    fun getTodo(id: Long) {
        viewModelScope.launch {
            _singleTodo.value = ApiResult.Progress
            val result = repository.getTodo(id)
            _singleTodo.value = result
        }
    }

    fun getTodos() {
        viewModelScope.launch {
            _todos.value = ApiResult.Progress
            val result = repository.getTodos()
            _todos.value = result
        }
    }
}
