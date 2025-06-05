package com.example.playwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playwithcompose.ui.theme.AppTheme
import com.example.playwithcompose.ui.theme.model.BodyActivity
import com.example.playwithcompose.ui.theme.model.FavoriteCollection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        value = "",
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        onValueChange = { },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    )
}

@Composable
fun BodyActivityElement(
    modifier: Modifier = Modifier,
    bodyActivity: BodyActivity
) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = modifier.clip(RoundedCornerShape(88.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(bodyActivity.drawable),
            contentDescription = null
        )
        Text(
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            text = bodyActivity.text,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun BodyActivities(
    modifier: Modifier = Modifier,
    bodyActivities: List<BodyActivity> = emptyList()
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(bodyActivities) {
            BodyActivityElement(
                modifier = modifier,
                bodyActivity = it
            )
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    favoriteCollection: FavoriteCollection
) {
    Surface (
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 8.dp
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ){
            Image(
                modifier = modifier.size(80.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = favoriteCollection.drawable),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(16.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = favoriteCollection.text,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun FavoriteCollections(
    modifier: Modifier = Modifier,
    favoriteCollections: List<FavoriteCollection>
) {
    LazyHorizontalGrid(
        modifier = modifier.height(168.dp),
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(favoriteCollections) {
            FavoriteCollectionCard(
                modifier = modifier,
                favoriteCollection = it
            )
        }
    }
}

// For bigger projects, we should host this in a separate file
// that connects all the screens together.
@Composable
fun HomeScreenBottomNavigation(
    modifier: Modifier = Modifier
) {
    var clickedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            selected = clickedItemIndex == 0,
            onClick = {
                clickedItemIndex = 0
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(
            selected = clickedItemIndex == 1,
            onClick = {
                clickedItemIndex = 1
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Profile")
            }
        )
    }
}

// TODO: Refactor the code here
@Composable
fun HomeScreenContent(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar()
        Spacer(modifier = Modifier.padding(top = 32.dp))
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Align your body",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall
        )
        BodyActivities(
            modifier = Modifier,
            bodyActivities = listOf(
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Inversions"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Quick yoga"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Stretching"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Tabata"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "HIIT"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Pre-natal yoga"
                )
            )
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Favorite Collections",
            style = MaterialTheme.typography.headlineSmall
        )
        FavoriteCollections(
            modifier = Modifier,
            favoriteCollections = listOf(
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Short mantras"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Nature meditations"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Stress and anxiety"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Self massage"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Overwhelmed"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Night wind down"
                )
            )
        )
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            HomeScreenBottomNavigation()
        }
    ) { padding ->
        HomeScreenContent(padding = padding)
    }
}

// Previews
@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    AppTheme {
        SearchBar(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun BodyActivityPreview() {
    AppTheme {
        BodyActivityElement(
            modifier = Modifier,
            bodyActivity = BodyActivity(
                drawable = R.drawable.ic_launcher_background,
                text = "Nature meditations"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BodyActivitiesPreview() {
    AppTheme {
        BodyActivities(
            modifier = Modifier,
            bodyActivities = listOf(
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Inversions"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Quick yoga"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Stretching"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Tabata"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "HIIT"
                ),
                BodyActivity(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Pre-natal yoga"
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    AppTheme {
        FavoriteCollectionCard(
            modifier = Modifier,
            favoriteCollection = FavoriteCollection(
                drawable = R.drawable.ic_launcher_background,
                text = "Self-massage"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionsPreview() {
    AppTheme {
        FavoriteCollections(
            modifier = Modifier,
            favoriteCollections = listOf(
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Short mantras"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Nature meditations"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Stress and anxiety"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Self massage"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Overwhelmed"
                ),
                FavoriteCollection(
                    drawable = R.drawable.ic_launcher_background,
                    text = "Night wind down"
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenBottomNavigationPreview() {
    AppTheme {
        HomeScreenBottomNavigation()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}
