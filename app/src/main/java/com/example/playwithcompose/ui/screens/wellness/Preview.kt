package com.example.playwithcompose.ui.screens.wellness

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.playwithcompose.ui.model.WellnessTask
import com.example.playwithcompose.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
internal fun CounterPreview() {
    AppTheme {
        Counter()
    }
}

@Preview(showBackground = true)
@Composable
internal fun WellnessTaskItemPreview() {
    AppTheme {
        WellnessTaskItem()
    }
}

@Preview(showBackground = true)
@Composable
internal fun WellnessTaskPreview() {
    AppTheme {
        WellnessTaskList(
            taskList = listOf(
                WellnessTask(1, "Task # 1"),
                WellnessTask(2, "Task # 2"),
                WellnessTask(3, "Task # 3")
            )
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
