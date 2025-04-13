// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}


buildscript {
    val kotlinVersion by extra("1.9.0") // Set the Kotlin version you're using
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Ensure you have the Kotlin Gradle Plugin defined
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // Google services plugin for Firebase and other Google services
        classpath("com.google.gms:google-services:4.3.15") // Update to the latest version if needed
    }
}