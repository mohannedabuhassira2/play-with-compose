package com.example.playwithcompose.ui.screens.wellness

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playwithcompose.R
import com.example.playwithcompose.ui.model.WellnessTask
import com.example.playwithcompose.ui.theme.AppTheme

@Composable
internal fun Counter(
    modifier: Modifier = Modifier,
    onIncrement: () -> Unit = {},
    enabled: Boolean = true
) {
    var context = LocalContext.current

    FloatingActionButton(
        modifier = modifier.size(64.dp),
        onClick = {
            if (enabled) {
                onIncrement()
            } else {
                Toast.makeText(context, "Button is disabled!", Toast.LENGTH_SHORT).show()
            }
        },
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Composable
internal fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String = "This is a task",
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    onClose: () -> Unit = {}
) {
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
            onCheckedChange = onCheckedChange
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
    onTaskClose: (WellnessTask) -> Unit = {},
    onTaskChecked: (WellnessTask, Boolean) -> Unit = { _, _ -> }
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(taskList, key = { it.id }) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onTaskChecked(task, checked) },
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
        modifier = modifier,
        floatingActionButton = {
            Counter(
                modifier = modifier.padding(16.dp),
                onIncrement = { viewModel.addTask() },
                enabled = viewModel.enableAddingTasks()
            )
        }
    ) { padding ->
        WellnessTaskList(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            taskList = viewModel.tasks,
            onTaskClose = { viewModel.closeTask(it) },
            onTaskChecked = { task, checked -> viewModel.changeTaskChecked(task, checked) }
        )
    }
}

@Composable
@Preview(showBackground = true)
internal fun WellnessScreenPreview() {
    AppTheme {
        WellnessScreen()
    }
}
