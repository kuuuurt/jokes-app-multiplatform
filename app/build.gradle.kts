plugins {
    id("com.android.application")
    id("kotlin-android")
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
    implementation(project(":common"))


    val kotlinVersion = rootProject.extra["kotlinVersion"]
    val navigationVersion = rootProject.extra["navigationVersion"]
    val coroutinesVersion = rootProject.extra["coroutinesVersion"]

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // LiveData and ViewModel
    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // AndroidX
    implementation("androidx.appcompat:appcompat:1.1.0-rc01")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.activity:activity-ktx:1.0.0-rc01")
    implementation("androidx.fragment:fragment-ktx:1.1.0-rc04")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")

    // Material Components
    implementation("com.google.android.material:material:1.1.0-alpha09")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}