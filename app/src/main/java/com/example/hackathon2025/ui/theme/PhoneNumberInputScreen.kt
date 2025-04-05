package com.example.hackathon2025

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern

@Composable
fun PhoneNumberInputScreen(onSwitchToOTPScreen: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }

    val phoneNumberRegex = Pattern.compile("^[0-9]{10}$")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Please enter your phone number to get started",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    phoneNumber = newValue
                }
                validationMessage = if (phoneNumberRegex.matcher(phoneNumber).matches()) {
                    ""
                } else {
                    "Phone number must be 10 digits"
                }
            },
            label = { Text("Enter Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )

        if (validationMessage.isNotEmpty()) {
            Text(
                text = validationMessage,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = { onSwitchToOTPScreen() },
            enabled = phoneNumberRegex.matcher(phoneNumber).matches(),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Next")
        }
    }
}

