plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId ="com.kurt.jokes"
        minSdkVersion(21)
        targetSdkVersion(29)
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

    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
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


    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // LiveData and ViewModel
    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // AndroidX
    val FRAGMENT = "1.3.0-alpha07"
    implementation("androidx.appcompat:appcompat:1.1.0-rc01")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.activity:activity-ktx:1.0.0-rc01")
    implementation("androidx.fragment:fragment-ktx:$FRAGMENT")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")

    // Material Components
    implementation("com.google.android.material:material:1.1.0-alpha09")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val ANDROIDX_TEST = "1.3.0"
    val ESPRESSO = "3.3.0"
    androidTestImplementation("androidx.test:core-ktx:${ANDROIDX_TEST}")
    androidTestImplementation("androidx.test:runner:${ANDROIDX_TEST}")
    androidTestImplementation("androidx.test:rules:${ANDROIDX_TEST}")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:$ESPRESSO")
    androidTestImplementation("androidx.fragment:fragment-testing:${FRAGMENT}")
    androidTestImplementation("androidx.navigation:navigation-testing:$navigationVersion")
    androidTestImplementation("com.schibsted.spain:barista:3.6.0")
}