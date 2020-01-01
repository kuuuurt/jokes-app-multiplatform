import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
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

kotlin {
    cocoapods {
        summary = "Shared module for Android and iOS"
        homepage = "Link to a Kotlin/Native module homepage"
    }

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        compilations {
            val main by getting {
                kotlinOptions.freeCompilerArgs = listOf("-Xobjc-generics")
            }
        }
    }
    android()


    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.3")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")
        implementation("io.ktor:ktor-client-json:1.2.6")
        implementation("io.ktor:ktor-client-serialization:1.2.6")
        implementation("io.ktor:ktor-client-core:1.2.6")
        implementation("com.squareup.sqldelight:runtime:1.2.1")
    }

    sourceSets["iosMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")
        implementation("io.ktor:ktor-client-json-native:1.2.6")
        implementation("io.ktor:ktor-client-serialization-native:1.2.6")
        implementation("io.ktor:ktor-client-ios:1.2.6")
        implementation("com.squareup.sqldelight:ios-driver:1.2.1")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    implementation("io.ktor:ktor-client-json-jvm:1.2.6")
    implementation("io.ktor:ktor-client-serialization-jvm:1.2.6")
    implementation("io.ktor:ktor-client-android:1.2.6")
    implementation("com.squareup.sqldelight:android-driver:1.2.1")

    // LiveData and ViewModel
    val lifecycleVersion = "2.2.0-rc03"
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
        }
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