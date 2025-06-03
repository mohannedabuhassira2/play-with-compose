package com.example.playwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Text(text = stringResource(R.string.welcome_onboarding_message))
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
            Text(text = stringResource(R.string.continue_to_greetings))
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
            Column {
                GreetingCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    name = greeting.toString()
                )
            }
        }
    }
}

@Composable
fun GreetingCard(
    modifier: Modifier = Modifier,
    name: String,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .shadow(32.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(32.dp)
    ) {
        Row {
            Greeting(
                modifier = Modifier.weight(1f),
                name = name
            )
            IconButton(
                onClick = {
                    isExpanded = !isExpanded
                }
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.expand),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        if (isExpanded) {
            Text(
                modifier = modifier.padding(4.dp),
                text = "Filler \ntext \nin \nhere\n...",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
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
            text = stringResource(R.string.hello),
            color = color
        )
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
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
