package com.example.playwithcompose.ui.screens.wellness

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.playwithcompose.ui.model.WellnessTask
import kotlin.collections.plusAssign

internal class WellnessViewModel : ViewModel() {
    private var currentId: Int = 1
    val tasks = mutableStateListOf<WellnessTask>()

    fun addTask() {
        tasks += WellnessTask(currentId++, "Task #${tasks.size}")
    }

    fun closeTask(task: WellnessTask) {
        tasks -= task
    }
}