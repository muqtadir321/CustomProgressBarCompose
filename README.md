# Custom Progress Bar Compose

## Description
Custom Progress Bar Compose is a Jetpack Compose library for Android that provides a customizable step progress bar, ideal for tracking multi-step processes in your Compose app. Easily configurable and fully customizable, this library lets developers add a sleek and functional progress indicator to their projects with minimal setup.

Features:

Flexible step count and labels
Customizable colors, icons, and styles
Intuitive setup for Jetpack Compose project

## Prerequisites
Make sure you have the following installed:
- Android Studio
- JDK (Java Development Kit)
- Gradle

## Getting Started

### Step 1: Add JitPack Repository
To use the Custom Progress Bar in your project, you need to include JitPack as a repository.

Add the following code to your root `build.gradle` file at the end of the `repositories` section:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### **Step 2: Add Dependency**
```groovy
implementation 'com.github.muqtadir321:CustomProgressBarCompose:1.0.4'
```
### Usage
Provide examples of how to use the Custom Progress Bar in your project. Include code snippets and screenshots.
 ```groovy
@Composable
fun MyCustomProgressBar() {
    StepProgressBar(
        currentStep = 2, // Set your progress value here
        steps = List<String>,
        color = Color.Blue, // Customize the color
    )
}

