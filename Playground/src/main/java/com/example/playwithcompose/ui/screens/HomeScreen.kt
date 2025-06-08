package com.example.playwithcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playwithcompose.data.networking.ApiResult
import com.example.playwithcompose.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val todo by viewModel.todoState.collectAsState()

    Box (
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val result = todo) {
            is ApiResult.Progress -> {
                CircularProgressIndicator()
            }
            is ApiResult.Success -> {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Todo: ${result.data}"
                )
            }
            is ApiResult.Failed -> {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Error icon",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        LaunchedEffect(Unit) {
            viewModel.getTodo(1)
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 480, heightDp = 480)
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}


