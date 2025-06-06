package com.example.playwithcompose.ui.theme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.playwithcompose.ui.theme.model.WellnessTask

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

@Preview(showBackground = true)
@Composable
internal fun WellnessTaskPreview() {
    AppTheme {
        WellnessTaskList()
    }
}
