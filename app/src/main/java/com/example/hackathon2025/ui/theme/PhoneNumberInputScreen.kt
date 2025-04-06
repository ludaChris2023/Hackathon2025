package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Shadow

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
        )



        {

            Box(
                modifier = Modifier
            ) {
                // Title Image
                Image(
                    painter = painterResource(id = R.drawable.wwt), // replace with your image's resource ID
                    contentDescription = "Header Image", // Description for accessibility
                    modifier = Modifier
                        .fillMaxWidth() // Make the image take up the full width of the screen
                        .height(200.dp) // Increase the height for a larger image
                        .align(Alignment.TopCenter) // Align the image at the top center
                )



            }


            Spacer(modifier = Modifier.height(0.dp))

            // Subtitle
            Text(

                text = "Conscious Free Gambling",
                style = TextStyle(
                    fontSize = 30.sp, // Larger font size
                    fontWeight = FontWeight.Bold, // Bold font weight
                    letterSpacing = 1.5.sp, // Adjust letter spacing
                    lineHeight = 40.sp, // Adjust line height for better spacing
                    color = Color(0xffe8c547), // We apply gradient color separately
                ),
                fontStyle = FontStyle.Italic // Italicize the text


            )

            Spacer(modifier = Modifier.height(50.dp))

            // Instruction Text
            Text(
                text = "Please enter your phone number to get started",
                style = TextStyle(
                    fontSize = 16.sp, // Slightly smaller font size
                    fontWeight = FontWeight.Bold, // Bold font weight to match
                    letterSpacing = 1.2.sp, // Adjust letter spacing to match
                    lineHeight = 28.sp, // Adjust line height for better spacing
                    color = Color(0xFF56CCF2), // White color to contrast with gradient header
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 3f
                    ) // Add shadow for consistency with the header text
                ),
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
                textStyle = LocalTextStyle.current.copy(color = Color(0xffe8c547))
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
