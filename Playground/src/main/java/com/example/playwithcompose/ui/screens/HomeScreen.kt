package com.example.playwithcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playwithcompose.R
import com.example.playwithcompose.data.networking.ApiResult
import com.example.playwithcompose.data.networking.model.Todo
import com.example.playwithcompose.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val todos by viewModel.todos

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                when (val result = todos) {
                    is ApiResult.Progress -> {
                        CircularProgressIndicator()
                    }
                    is ApiResult.Success -> {
                        TodoList(
                            modifier = Modifier.fillMaxSize(),
                            todoList = result.data
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
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.getTodos()
    }
}

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    todoList: List<Todo>
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(todoList) { todo ->
            TodoItem(
                modifier = modifier,
                todo = todo
            )
        }
    }
}

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    todo: Todo
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Todo #${todo.id}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = if (todo.completed) stringResource(R.string.completed) else stringResource(R.string.pending),
                style = MaterialTheme.typography.bodySmall,
                color = if (!todo.completed) MaterialTheme.colorScheme.error else Color.Green,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 480, heightDp = 480)
fun HomeScreenPreview() {
    AppTheme {
        TodoItem(
            todo = Todo(
                userId = 1,
                id = 1,
                title = "Sample Todo",
                completed = true
            )
        )
    }
}
