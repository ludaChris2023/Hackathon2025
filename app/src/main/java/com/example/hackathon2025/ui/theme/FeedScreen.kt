package com.example.hackathon2025

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.* // Import all the filled icons
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
import androidx.compose.foundation.lazy.LazyColumn
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF2D2D2D)),
                actions = {
                    IconButton(
                        onClick = { /* Handle click */ },
                        modifier = Modifier.padding(end = 24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(48.dp),
                            tint = Color(0xffe8c547)
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
        // Feed content box with white background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color (0xFF2D2D2D)),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp), // Space between items
            ) {
                items(5) { index -> // Creates 5 items (boxes)
                    FeedItem(game) // Render the same box (team feed item)
                }
            }
        }
    }
}

@Composable
fun FeedItem(game: Game) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Set fixed height for each item
            .background(Color.White)
            .border(width = 2.dp, color = Color.Black)
            .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
    ) {
        Column(
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
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(0.4f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe8c547))
            ) {
                Text("Bet", fontSize = 20.sp)
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
        Text(odds, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavItem(label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        val icon = when (label) {
            "My Bets" -> Icons.Default.Money // Relevant icon for "My Bets"
            "Home" -> Icons.Default.Home // Relevant icon for "Home"
            "My Stats" -> Icons.Default.BarChart // Relevant icon for "My Stats"
            else -> Icons.Default.Place // Default icon
        }

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xffe8c547),  // Set color to yellow
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}