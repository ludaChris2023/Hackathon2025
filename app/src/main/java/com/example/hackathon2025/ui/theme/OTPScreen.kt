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
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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

            // Title
            Text(

                text = "OTP Verification",
                style = TextStyle(
                    fontSize = 30.sp, // Larger font size
                    fontWeight = FontWeight.Bold, // Bold font weight
                    letterSpacing = 1.5.sp, // Adjust letter spacing
                    lineHeight = 40.sp, // Adjust line height for better spacing
                    color = Color(0xffe8c547), // We apply gradient color separately
                ),
                fontStyle = FontStyle.Italic // Italicize the text


            )

            // Instruction Text

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Please enter the $otpLength-digit OTP",
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
                textStyle = LocalTextStyle.current.copy(Color(0xffe8c547))
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
