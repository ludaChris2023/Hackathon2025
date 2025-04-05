package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
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
            .padding(16.dp)
    ) {
        // Leagues Section
        Text("Leagues", style = MaterialTheme.typography.titleMedium)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isNHLChecked,
                onCheckedChange = { isNHLChecked = it }
            )
            Text("NHL", modifier = Modifier.padding(start = 8.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isNFLChecked,
                onCheckedChange = { isNFLChecked = it }
            )
            Text("NFL", modifier = Modifier.padding(start = 8.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isNBAChecked,
                onCheckedChange = { isNBAChecked = it }
            )
            Text("NBA", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Favorite Charity Section
        Text("Favorite Charity", style = MaterialTheme.typography.titleMedium)

        // Charity Name Input
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Charity Name: ", modifier = Modifier.width(100.dp))
            BasicTextField(
                value = charityName,
                onValueChange = { charityName = it },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary) // Apply border
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Charity Payment Link Input
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Payment Link: ", modifier = Modifier.width(100.dp))
            BasicTextField(
                value = charityPaymentLink,
                onValueChange = { charityPaymentLink = it },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary) // Apply border
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                // Handle saving preferences
                // Here you could save the preferences locally, or in a ViewModel, etc.
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save Preferences")
        }
    }
}