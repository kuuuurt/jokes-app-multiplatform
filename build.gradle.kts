buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val sqldelight: String by project
    val kotlin: String by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
        classpath("com.android.tools.build:gradle:7.0.0-beta05")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqldelight")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}