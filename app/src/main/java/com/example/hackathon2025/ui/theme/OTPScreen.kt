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
import androidx.compose.ui.platform.LocalContext

@Composable
fun OtpVerificationScreen(onSwitchToPreferencesScreen: () -> Unit) {
    var otp by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }
    val otpLength = 6  // Assuming OTP is 6 digits
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter OTP",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = otp,
            onValueChange = { newValue ->
                if (newValue.length <= otpLength && newValue.all { it.isDigit() }) {
                    otp = newValue
                }
                validationMessage = if (otp.length == otpLength) {
                    ""
                } else {
                    "OTP must be $otpLength digits long"
                }
            },
            label = { Text("OTP") },
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
                if (otp.length == otpLength) {
                    Toast.makeText(context, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
                    onSwitchToPreferencesScreen()  // Switch to Preferences screen
                } else {
                    validationMessage = "Please enter a valid OTP."
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Verify OTP")
        }
    }
}

