package com.example.playwithcompose.ui.screens.wellness

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.playwithcompose.ui.model.WellnessTask
import kotlin.collections.plusAssign

internal class WellnessViewModel : ViewModel() {
    private var currentId: Int = 1

    var count: MutableState<Int> = mutableIntStateOf(0)
        private set

    val tasks = mutableStateListOf<WellnessTask>()

    fun addTask() {
        tasks += WellnessTask(currentId, "Task #${currentId}")
        currentId++
        count.value++
    }

    fun closeTask(task: WellnessTask) {
        count.value--
        tasks -= task
    }

    fun changeTaskChecked(
        task: WellnessTask,
        updatedCheck: Boolean
    ) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = tasks[index].copy(
                checked = updatedCheck
            )
        }
    }

    fun enableAddingTasks(): Boolean = count.value < 10
}