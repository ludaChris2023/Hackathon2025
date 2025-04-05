package com.example.hackathon2025

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import com.example.hackathon2025.ui.theme.Hackathon2025Theme

@Composable
fun PhoneNumberInputScreen() {
    var phoneNumber by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }

    val phoneNumberRegex = Pattern.compile("^[0-9]{10}$")

    // Get the context from the local context
    val context = LocalContext.current

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
            onClick = {
                if (phoneNumberRegex.matcher(phoneNumber).matches()) {
                    // Create an Intent to start the OtpVerificationScreen
                    val intent = Intent(context, OtpVerificationScreen::class.java)
                    intent.putExtra("phoneNumber", phoneNumber)  // Pass phone number data
                    context.startActivity(intent)  // Start the new activity
                } else {
                    validationMessage = "Please enter a valid 10-digit phone number."
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Next")
        }
    }
}
