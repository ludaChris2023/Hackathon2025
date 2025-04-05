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

class PasswordVerificationScreen : ComponentActivity() {
    private var password = mutableStateOf("")
    private var passwordMessage = mutableStateOf("")

    private val passwordRegex = Pattern.compile("^[a-zA-Z0-9]{6,}$")

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
                    Text(text = "Password Verification for $phoneNumber")

                    TextField(
                        value = password.value,
                        onValueChange = { newValue ->
                            password.value = newValue
                            passwordMessage.value = if (passwordRegex.matcher(password.value).matches()) {
                                ""
                            } else {
                                "Password must be at least 6 characters long"
                            }
                        },
                        label = { Text("Enter Password") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary)
                            .padding(16.dp)
                    )

                    if (passwordMessage.value.isNotEmpty()) {
                        Text(
                            text = passwordMessage.value,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Button(
                        onClick = {
                            if (passwordRegex.matcher(password.value).matches()) {
                                val intent = Intent(this@PasswordVerificationScreen, ProfileScreen::class.java)
                                intent.putExtra("phoneNumber", phoneNumber)
                                startActivity(intent)
                            } else {
                                passwordMessage.value = "Please enter a valid password."
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Verify Password")
                    }
                }
            }
        }
    }
}
