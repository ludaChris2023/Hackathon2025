package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hackathon2025.ui.theme.Hackathon2025Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hackathon2025Theme {
                // Track the screen name to decide which screen to show
                var currentScreen by remember { mutableStateOf("PhoneNumberScreen") }

                // Use 'when' to conditionally display the screen based on the currentScreen value
                when (currentScreen) {
                    "PhoneNumberScreen" -> PhoneNumberInputScreen { currentScreen = "OtpScreen" }
                    "OtpScreen" -> OtpVerificationScreen { currentScreen = "PreferencesScreen" }
                    "PreferencesScreen" -> PreferencesScreen()  // No need to change screen here
                }
            }
        }
    }
}