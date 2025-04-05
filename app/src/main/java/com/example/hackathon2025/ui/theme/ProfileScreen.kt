package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.hackathon2025.ui.theme.Hackathon2025Theme

class ProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phoneNumber = intent.getStringExtra("phoneNumber") ?: ""

        setContent {
            Hackathon2025Theme {
                // Display the phone number or any other information you'd like
                Text(text = "Profile Screen for $phoneNumber")
            }
        }
    }
}
