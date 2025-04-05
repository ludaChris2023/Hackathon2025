package com.example.hackathon2025

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val Team1: String,
    val Team2: String,
    val Spread: Int,
    val Team1Logo: String,
    val Team2Logo: String,
    val League: String
)
