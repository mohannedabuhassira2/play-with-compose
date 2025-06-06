package com.example.playwithcompose.ui.theme

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.playwithcompose.ui.theme.model.WellnessTask

internal class WellnessViewModel : ViewModel() {
    val tasks = mutableStateListOf<WellnessTask>()

    fun addTask() {
        tasks += WellnessTask(tasks.size, "Task #${tasks.size}")
    }

    fun closeTask(task: WellnessTask) {
        tasks -= task
    }
}
