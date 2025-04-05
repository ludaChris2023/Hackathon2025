package com.example.hackathon2025.ui.theme

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json



class NHLFetcher {

    // Add an attribute to store the fetched NFL games
    var nhlGamesResponse: String? = null

    // Initialize the Ktor client with necessary features
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }) // Configure JSON serialization
        }
    }

    // Function to fetch NFL games asynchronously
    suspend fun fetchNHLGames(): String? {
        val url = "http://10.0.2.2:8080/fetch-Games-NHL" // Replace with actual URL if needed

        return try {
            // Perform the GET request and retrieve the response
            val response: String = client.get(url).bodyAsText()


            // Store the response in the class property
            nhlGamesResponse = response

            // Return the response
            nhlGamesResponse
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    // Close the Ktor client when it's no longer needed
    fun close() {
        client.close()
    }
}