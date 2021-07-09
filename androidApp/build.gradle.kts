plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId ="com.kuuuurt.jokes"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-rc01"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(project(":shared"))

    val kotlin: String by project
    val coroutines: String by project
    val navigationVersion = "2.2.0-rc04"

    // Compose
    val compose = "1.0.0-rc01"
    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.material:material:$compose")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin")

    // AndroidX
    implementation("androidx.appcompat:appcompat:1.4.0-alpha03")
    implementation("androidx.activity:activity-compose:1.3.0-rc01")
    implementation("androidx.activity:activity-ktx:1.3.0-rc01")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("com.google.android.material:material:1.3.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val ANDROIDX_TEST = "1.3.0"
    val ESPRESSO = "3.3.0"
    androidTestImplementation("androidx.test:core-ktx:${ANDROIDX_TEST}")
    androidTestImplementation("androidx.test:runner:${ANDROIDX_TEST}")
    androidTestImplementation("androidx.test:rules:${ANDROIDX_TEST}")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:$ESPRESSO")
    androidTestImplementation("com.schibsted.spain:barista:3.6.0")
}