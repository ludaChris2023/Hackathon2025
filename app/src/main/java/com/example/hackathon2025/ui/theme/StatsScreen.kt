package com.example.hackathon2025

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

// Import BottomNavItem from FeedScreen
import com.example.hackathon2025.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen() {
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
        // Content of the Stats Screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp) // To adjust below the notification bell
            ) {
                // Stats Title
                Text(
                    text = "My Stats",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold, // Make the title bold
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Stats List
                StatItem("Favorite Charity", "Charity Name")
                StatItem("Money Donated to Charities", "$500")
                StatItem("Money Won", "$1200")
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label: $value",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

