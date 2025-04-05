package com.example.hackathon2025

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import coil.ImageLoader
import kotlinx.serialization.json.Json
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

// Json Vars
const val rawJson = """
    [{"Team1":"New York Giants","Team2":"New York Jets","Spread":100,
    "Team1Logo":"giants",
    "Team2Logo":"jets",
    "League":"NFL"}]
"""

val games = Json.decodeFromString<List<Game>>(rawJson)
val game = games.first() // For now, just pick the first one

//Image loader
@Composable
fun SvgImage(url: String) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = null,
        modifier = Modifier.size(64.dp) // adjust as needed
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(
                        onClick = { /* Handle click */ },
                        modifier = Modifier.padding(end = 24.dp) // move it more to the left
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(48.dp), // double the default 24.dp size
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(label = "My Bets")
                    BottomNavItem(label = "Home")
                    BottomNavItem(label = "My Stats")
                }
            }
        }
    ) { innerPadding ->
    // Feed content box
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.3f)
                .border(width = 2.dp, color = Color.Black)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Teams row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TeamColumn(name = game.Team1, odds = "+${game.Spread}", logoName = game.Team1Logo)
                TeamColumn(name = game.Team2, odds = "-${game.Spread}", logoName = game.Team2Logo)
            }
            // Bet button
            Button(
                onClick = { /* Handle bet click */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Bet", fontSize = 20.sp)
            }

        }
    }
}
}

@Composable
fun TeamColumn(name: String, odds: String, logoName: String) {
    val imageRes = when (logoName) {
        "giants" -> R.drawable.giants_logo
        "jets" -> R.drawable.jets_logo
        else -> R.drawable.placeholder_logo // add a default placeholder
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Text(name, fontSize = 18.sp)
        Text(odds, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavItem(label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Place, // Placeholder icon
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = label,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PreferencesScreen(onNavigateToFeed: () -> Unit) { // Added a callback function
    var isNHLChecked by remember { mutableStateOf(false) }
    var isNFLChecked by remember { mutableStateOf(false) }
    var isNBAChecked by remember { mutableStateOf(false) }

    var charityName by remember { mutableStateOf("") }
    var charityPaymentLink by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D))
            .padding(16.dp)
    ) {
        // Leagues Section
        Text(
            "Leagues",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNHLChecked,
                onCheckedChange = { isNHLChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Text(
                "NHL",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNFLChecked,
                onCheckedChange = { isNFLChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Text(
                "NFL",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNBAChecked,
                onCheckedChange = { isNBAChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Text(
                "NBA",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Favorite Charity Section
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = charityName,
            onValueChange = { newValue ->

                charityName = newValue
            },
            label = { Text("Enter Charity Name", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Charity Payment Link Input
        OutlinedTextField(
            value = charityPaymentLink,
            onValueChange = { newValue ->
                    charityPaymentLink = newValue
            },
            label = { Text("Enter Charity Payment Link", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                // Handle saving preferences
                // After saving (or for now, directly), navigate to the FeedScreen
                onNavigateToFeed()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Save Preferences", color = Color.White)
        }
    }
}