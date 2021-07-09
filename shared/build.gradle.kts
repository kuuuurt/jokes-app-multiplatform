import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.5.10"
    id("com.squareup.sqldelight")

}

sqldelight {
    database("JokesDatabase") {
        packageName = "com.kurt.jokes"
    }
}

version = "1.0.0"

val coroutines: String by project
val ktor: String by project
val sqldelight: String by project
val serialization: String by project

kotlin {
    cocoapods {
        summary = "Shared module for Android and iOS"
        homepage = "Link to a Kotlin/Native module homepage"
        frameworkName = "JokesShared"
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    android()
    iosTarget("ios") {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serialization")
                implementation("io.ktor:ktor-client-json:$ktor")
                implementation("io.ktor:ktor-client-serialization:$ktor")
                implementation("io.ktor:ktor-client-core:$ktor")
                implementation("io.ktor:ktor-client-cio:$ktor")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktor")
                implementation("com.squareup.sqldelight:native-driver:$sqldelight")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
                implementation("org.jetbrains.kotlin:kotlin-test-junit")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktor")
                implementation("com.squareup.sqldelight:android-driver:$sqldelight")

                // LiveData and ViewModel
                val lifecycleVersion = "2.2.0"
                implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
            }
        }
    }
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            consumerProguardFiles("consumer-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
