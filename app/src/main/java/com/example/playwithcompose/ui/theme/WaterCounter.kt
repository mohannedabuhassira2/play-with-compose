package com.example.playwithcompose.ui.theme

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun Counter(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    var count by remember { mutableIntStateOf(0) }

    Counter(
        modifier = modifier,
        onIncrement = {
            count++
            onClick()
        },
        enabled = count < 100
    )
}

@Composable
internal fun Counter(
    modifier: Modifier = Modifier,
    onIncrement: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onIncrement,
        enabled = enabled
    ) {
        Text(
            text = "Add one",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun CounterPreview() {
    AppTheme {
        Counter()
    }
}
