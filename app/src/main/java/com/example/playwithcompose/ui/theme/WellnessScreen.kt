package com.example.playwithcompose.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playwithcompose.ui.theme.model.WellnessTask

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
                onClick = {
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

@Composable
@Preview(showBackground = true)
internal fun WellnessScreenPreview() {
    AppTheme {
        WellnessScreen()
    }
}
