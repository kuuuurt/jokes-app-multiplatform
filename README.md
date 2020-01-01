# Jokes App

A Kotlin Multiplatform sample 

## Multiplatform Project

This is a Kotlin/Multiplatform project uses code sharing between Android and iOS.

Both applications are fully native and only share the common logic between the platforms.

**android** - contains the Android project 

**ios** - contains the iOS project

**common** - contains the shared Kotlin code

## Android and Shared

Use **Android Studio** and open the project on the root of this repository.  Android Studio will be able to see the gradle modules both on the **android** and **shared** module.

## iOS

Use **Xcode** and open the **ios** folder or use **stark.xcworkspace**. The shared modules cannot be seen from this project. iOS gets the shared code through a framework which is exported from the Kotlin project.  

Unfortunately, modifying the shared code requires Android Studio or IntelliJ IDEA.


## Authors

- Kurt Renzo Acosta - [kurt.acosta@whitecloak.com](mailto:kurt.r.acosta@gmail.com)
