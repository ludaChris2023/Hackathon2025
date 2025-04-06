package com.example.hackathon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp // Add this import at the top of your file

import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PreferencesScreen(onNextScreen: () -> Unit) {
    var isNHLChecked by remember { mutableStateOf(false) }
    var isNFLChecked by remember { mutableStateOf(false) }
    var isNBAChecked by remember { mutableStateOf(false) }

    var charityName by remember { mutableStateOf("") }
    var charityPaymentLink by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D))
            .padding(16.dp)
    )


    {

        // Image at the top
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.wwt), // replace with your image's resource ID
            contentDescription = "Header Image", // Description for accessibility
            modifier = Modifier
                .fillMaxWidth() // You can adjust this as needed
                .height(200.dp) // You can adjust the height based on your image size
        )

        // Leagues Section
        Spacer(modifier = Modifier.height(85.dp))
        // Subtitle
        Text(

            text = "Leagues",
            style = TextStyle(
                fontSize = 30.sp, // Larger font size
                fontWeight = FontWeight.Bold, // Bold font weight
                letterSpacing = 1.5.sp, // Adjust letter spacing
                lineHeight = 40.sp, // Adjust line height for better spacing
                color = Color(0xffe8c547), // We apply gradient color separately
            ),
            fontStyle = FontStyle.Italic // Italicize the text


        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNHLChecked,
                onCheckedChange = { isNHLChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Text(
                "NHL",
                fontSize = 24.sp, // Set the font size explicitly
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNFLChecked,
                onCheckedChange = { isNFLChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)

            )
            Text(
                "NFL",
                fontSize = 24.sp, // Set the font size explicitly
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White

            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isNBAChecked,
                onCheckedChange = { isNBAChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Text(
                "NBA",
                fontSize = 24.sp, // Set the font size explicitly
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Favorite Charity Section

        Spacer(modifier = Modifier.height(25.dp))

        Text(

            text = "Charity",
            style = TextStyle(
                fontSize = 30.sp, // Larger font size
                fontWeight = FontWeight.Bold, // Bold font weight
                letterSpacing = 1.5.sp, // Adjust letter spacing
                lineHeight = 40.sp, // Adjust line height for better spacing
                color = Color(0xffe8c547), // We apply gradient color separately
            ),
            fontStyle = FontStyle.Italic // Italicize the text


        )

        OutlinedTextField(
            value = charityName,
            onValueChange = { newValue ->
                charityName = newValue  // Allow any text input
            },
            label = { Text("Enter Charity Name", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color(0xffe8c547)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.White
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                onNextScreen()  // This line triggers the navigation to the next screen


                // Handle saving preferences
                // Here you could save the preferences locally, or in a ViewModel, etc.
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Save Preferences", color = Color.White)
        }
    }
}