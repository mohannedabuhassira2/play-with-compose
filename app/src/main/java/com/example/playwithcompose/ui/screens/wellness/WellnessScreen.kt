package com.example.playwithcompose.ui.screens.wellness

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playwithcompose.ui.model.WellnessTask

@Composable
internal fun Counter(
    modifier: Modifier = Modifier,
    onIncrement: () -> Unit = {},
    enabled: Boolean = true
) {
    var count by remember { mutableIntStateOf(0) }

    Button(
        modifier = modifier,
        onClick = {
            count++
            onIncrement()
        },
        enabled = enabled
    ) {
        Text(
            text = "Add one",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
internal fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String = "This is a task",
    onCheckedChange: (Boolean) -> Unit = {},
    onClose: () -> Unit = {}
) {
    var checked by rememberSaveable { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier.weight(1f),
            text = taskName,
            style = MaterialTheme.typography.titleMedium,
        )
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                onCheckedChange
            }
        )
        Icon(
            modifier = modifier.clickable { onClose() },
            imageVector = Icons.Filled.Close,
            contentDescription = null
        )
    }
}

@Composable
internal fun WellnessTaskList(
    modifier: Modifier = Modifier,
    taskList: List<WellnessTask> = emptyList(),
    onTaskClose: (WellnessTask) -> Unit = {}
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(taskList, key = { it.id }) { task ->
            WellnessTaskItem(
                taskName = task.label,
                onClose = { onTaskClose(task) }
            )
        }
    }
}

@Composable
internal fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Counter(
                modifier = modifier,
                onIncrement = {
                    viewModel.addTask()
                }
            )
            WellnessTaskList(
                modifier = modifier,
                taskList = viewModel.tasks,
                onTaskClose = { viewModel.closeTask(it) }
            )
        }
    }
}
