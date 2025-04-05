package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.background

@Composable
fun PhoneNumberInputScreen(onSwitchToOTPScreen: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }

    val phoneNumberRegex = Regex("^[0-9]{10}$")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D)) // Charcoal background
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Win Win",
                style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subtitle
            Text(
                text = "Conscious Free Gambling",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Instruction Text
            Text(
                text = "Please enter your phone number to get started",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Phone Number Input
            // Phone Number Input
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        phoneNumber = newValue
                    }
                    validationMessage = if (phoneNumber.matches(phoneNumberRegex)) {
                        ""
                    } else {
                        "Phone number must be 10 digits"
                    }
                },
                label = { Text("Enter Phone Number", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                isError = validationMessage.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
                // You might still want to customize other colors if needed
                // colors = TextFieldDefaults.outlinedTextFieldColors(
                //     focusedBorderColor = Color.White,
                //     unfocusedBorderColor = Color.Gray,
                //     focusedLabelColor = Color.White,
                //     unfocusedLabelColor = Color.Gray,
                //     cursorColor = Color.White
                // )
            )

            // Validation Message
            if (validationMessage.isNotEmpty()) {
                Text(
                    text = validationMessage,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Spacer
            Spacer(modifier = Modifier.height(20.dp))

            // Next Button
            Button(
                onClick = { onSwitchToOTPScreen() },
                enabled = phoneNumber.matches(phoneNumberRegex),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Next")
            }
        }
    }
}


