package com.example.playwithcompose.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String,
    onClose: () -> Unit = {}
) {
    var checked by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        checked = checked,
        modifier = modifier,
        taskName = taskName,
        onCheckedChange = { checked = it },
        onClose = onClose
    )
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

@Preview(showBackground = true)
@Composable
internal fun WellnessTaskItemPreview() {
    AppTheme {
        WellnessTaskItem()
    }
}
