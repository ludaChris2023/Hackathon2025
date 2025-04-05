package com.example.hackathon2025

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun OtpVerificationScreen(onSwitchToPreferencesScreen: () -> Unit) {
    var otp by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }
    val otpLength = 6 // Assuming OTP is 6 digits
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D)) // Charcoal background
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "OTP Verification",
                style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                textAlign = TextAlign.Center
            )

            // Instruction Text
            Text(
                text = "Please enter the $otpLength-digit OTP",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                textAlign = TextAlign.Center
            )

            // OTP Input Field
            OutlinedTextField(
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
                label = { Text("Enter OTP", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                isError = validationMessage.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
                // You might still want to customize other colors if needed
                // colors = TextFieldDefaults.outlinedTextFieldColors(
                //     textColor = Color.White,
                //     focusedBorderColor = Color.White,
                //     unfocusedBorderColor = Color.Gray,
                //     focusedLabelColor = Color.White,
                //     unfocusedLabelColor = Color.Gray,
                //     cursorColor = Color.White,
                //     errorBorderColor = Color.Red,
                //     errorLabelColor = Color.Red
                // )
            )

            // Validation Message
            if (validationMessage.isNotEmpty()) {
                Text(
                    text = validationMessage,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                    textAlign = TextAlign.Center
                )
            }

            // Verify OTP Button
            Button(
                onClick = {
                    if (otp.length == otpLength) {
                        Toast.makeText(context, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
                        onSwitchToPreferencesScreen() // Switch to Preferences screen
                    } else {
                        validationMessage = "Please enter a valid $otpLength-digit OTP."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Verify OTP")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun OtpVerificationScreenPreview() {
//    OtpVerificationScreen(onSwitchToPreferencesScreen = {})
//}