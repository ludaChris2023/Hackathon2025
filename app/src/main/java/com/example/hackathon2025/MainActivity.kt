package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import com.example.hackathon2025.ui.theme.Hackathon2025Theme
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hackathon2025Theme {
                PhoneNumberInputScreen()
            }
        }
    }
}

// Screen to enter phone number
@Composable
fun PhoneNumberInputScreen() {
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var validationMessage by remember { mutableStateOf("") }

    // Regular expression to validate 10-digit phone number
    val phoneNumberRegex = Pattern.compile("^[0-9]{10}$")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                if (newValue.text.all { it.isDigit() }) {
                    phoneNumber = newValue
                }
                validationMessage = if (phoneNumberRegex.matcher(phoneNumber.text).matches()) {
                    ""
                } else {
                    "Phone number must be 10 digits"
                }
            },
            label = { Text("Enter Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle the Done action (optional)
                }
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
                if (phoneNumberRegex.matcher(phoneNumber.text).matches()) {
                    // Proceed to OTP screen if the phone number is valid
                    // For now, we'll navigate to OTP screen with the phone number as the parameter
                    // (you can use a navigation library like NavController for real navigation)
                    validationMessage = "Phone number valid! Proceeding to OTP screen."
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

// OTP Verification Screen
@Composable
fun OtpVerificationScreen(phoneNumber: String) {
    var otp by remember { mutableStateOf(TextFieldValue("")) }
    var verificationMessage by remember { mutableStateOf("") }

    // Regular expression to validate 6-digit OTP
    val otpRegex = Pattern.compile("^[0-9]{6}$")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "OTP Verification for $phoneNumber")

        TextField(
            value = otp,
            onValueChange = { newValue ->
                if (newValue.text.all { it.isDigit() }) {
                    otp = newValue
                }
                verificationMessage = if (otpRegex.matcher(otp.text).matches()) {
                    ""
                } else {
                    "OTP must be 6 digits"
                }
            },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle the Done action (optional)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )

        if (verificationMessage.isNotEmpty()) {
            Text(
                text = verificationMessage,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                if (otpRegex.matcher(otp.text).matches()) {
                    verificationMessage = "OTP Verified! You are successfully logged in."
                } else {
                    verificationMessage = "Please enter a valid 6-digit OTP."
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Verify OTP")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhoneNumberInputPreview() {
    Hackathon2025Theme {
        PhoneNumberInputScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun OtpVerificationPreview() {
    Hackathon2025Theme {
        OtpVerificationScreen("1234567890")
    }
}
