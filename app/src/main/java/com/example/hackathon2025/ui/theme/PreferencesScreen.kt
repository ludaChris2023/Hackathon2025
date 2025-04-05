package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PreferencesScreen() {
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

        Spacer(modifier = Modifier.height(25.dp))

        // Favorite Charity Section
        OutlinedTextField(
            value = charityName,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    charityName = newValue
                }
            },
            label = { Text("Enter Charity Name", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
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
                if (newValue.all { it.isDigit() }) {
                    charityPaymentLink = newValue
                }
            },
            label = { Text("Enter Charity Payment Link", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
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
                // Here you could save the preferences locally, or in a ViewModel, etc.
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Save Preferences", color = Color.White)
        }
    }
}