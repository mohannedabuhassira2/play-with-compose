package com.example.playwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playwithcompose.ui.theme.PlayWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = !shouldShowOnboarding })
    } else {
        GreetingScreen()
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to the Basics Codelab!")
        Spacer(modifier = Modifier.height(8.dp))
        // TODO: Buttons with the app theme should be reused in a Composable
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                onContinueClicked()
            }
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun GreetingScreen(
    greetings: List<Int> = List(1000) { it }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        items(greetings) { greeting ->
            GreetingCard(
                modifier = Modifier.fillMaxWidth(),
                name = greeting.toString()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun GreetingCard(
    modifier: Modifier = Modifier,
    name: String
) {
    var buttonText by rememberSaveable { mutableStateOf("Show more") }
    var extraPadding by rememberSaveable { mutableIntStateOf(0) }

    val animatedPadding by animateDpAsState(
        targetValue = extraPadding.dp
    )

    Row(modifier = modifier
        .background(MaterialTheme.colorScheme.primary)
        .padding(24.dp)
    ) {
        Greeting(
            modifier = Modifier.weight(1f),
            name = name
        )
        Button(
            modifier = Modifier.padding(bottom = animatedPadding),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                buttonText = if (buttonText == "Show more") "Show less" else "Show more"
                extraPadding = if (extraPadding == 0) 48 else 0
            }
        ) {
            Text(text = buttonText)
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    name: String,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {
    Column(modifier = modifier) {
        Text(
            text = "Hello",
            color = color
        )
        Text(
            text = "$name!",
            color = color
        )
    }
}

// Previews
@Preview(name = "Light Mode", showBackground = true, widthDp = 480, heightDp = 480)
@Composable
fun GreetingPreview() {
    PlayWithComposeTheme {
        GreetingScreen()
    }
}
@Preview(showBackground = true, widthDp = 480, heightDp = 480)
@Composable
fun OnboardingPreview() {
    PlayWithComposeTheme {
        OnboardingScreen()
    }
}
