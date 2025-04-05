plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.hackathon2025"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hackathon2025"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("io.coil-kt:coil-compose:2.4.0") // for loading images from URLs
    implementation("io.coil-kt:coil-svg:2.4.0")

// Ktor client dependencies
    implementation("io.ktor:ktor-client-core:2.3.0") // Ktor client core
    implementation("io.ktor:ktor-client-cio:2.3.0")  // For CIO engine
    implementation("io.ktor:ktor-client-serialization:2.3.0") // For Ktor serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.3.0") // For content negotiation
    implementation("io.ktor:ktor-client-request:2.3.0") // For making requests
    implementation("io.ktor:ktor-client-statement:2.3.0") // For handling responses
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0") // JSON serialization support
    implementation("io.ktor:ktor-client-json:2.3.0") // JSON support (optional, if needed)

// Coroutines dependencies
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0") // For coroutines


}