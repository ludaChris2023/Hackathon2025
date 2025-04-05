package com.example.hackathon2025

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern
import com.example.hackathon2025.ui.theme.Hackathon2025Theme // Import Hackathon2025Theme

class OtpVerificationScreen : ComponentActivity() {
    private var otp = mutableStateOf("")
    private var verificationMessage = mutableStateOf("")

    private val otpRegex = Pattern.compile("^[0-9]{6}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phoneNumber = intent.getStringExtra("phoneNumber") ?: ""

        setContent {
            Hackathon2025Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "OTP Verification for $phoneNumber")

                    TextField(
                        value = otp.value,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) {
                                otp.value = newValue
                            }
                            verificationMessage.value = if (otpRegex.matcher(otp.value).matches()) {
                                ""
                            } else {
                                "OTP must be 6 digits"
                            }
                        },
                        label = { Text("Enter OTP") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary)
                            .padding(16.dp)
                    )

                    if (verificationMessage.value.isNotEmpty()) {
                        Text(
                            text = verificationMessage.value,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Button(
                        onClick = {
                            if (otpRegex.matcher(otp.value).matches()) {
                                val intent = Intent(this@OtpVerificationScreen, PasswordVerificationScreen::class.java)
                                intent.putExtra("phoneNumber", phoneNumber)
                                startActivity(intent)
                            } else {
                                verificationMessage.value = "Please enter a valid 6-digit OTP."
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Verify OTP")
                    }
                }
            }
        }
    }
}
