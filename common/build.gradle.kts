import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("JokesDatabase") {
        packageName = "com.kurt.jokes"
    }
}

version = "1.0.0"

val coroutinesVersion: String by rootProject.extra
val ktorVersion: String by rootProject.extra
val sqlDelightVersion: String by rootProject.extra
val serializationVersion: String by rootProject.extra

kotlin {
    cocoapods {
        summary = "Shared module for Android and iOS"
        homepage = "Link to a Kotlin/Native module homepage"
        frameworkName = "Common"
    }

//    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
//        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
//            ::iosArm64
//        else
//            ::iosX64
//
//    iOSTarget("ios") {
//        compilations {
//            val main by getting {
//                kotlinOptions.freeCompilerArgs = listOf("-Xobjc-generics")
//            }
//        }
//    }
    ios()
    android()


    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
        implementation("io.ktor:ktor-client-json:$ktorVersion")
        implementation("io.ktor:ktor-client-serialization:$ktorVersion")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
    }

    sourceSets["iosMain"].dependencies {
        implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
    }

    sourceSets["androidMain"].dependencies {
        implementation("io.ktor:ktor-client-android:$ktorVersion")
        implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

        // LiveData and ViewModel
        val lifecycleVersion = "2.2.0"
        implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
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