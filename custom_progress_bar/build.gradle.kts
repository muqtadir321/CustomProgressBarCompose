plugins {
//    alias(libs.plugins.android.library)
//    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.android.library")
//    id("maven-publish")
    kotlin("android")
//    `maven-publish`
    id("maven-publish")
}

android {
    namespace = "com.example.custom_progress_bar"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.androidx.ui)
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.graphics)
    implementation(platform(libs.androidx.compose.bom))

}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.muqtadir321"
                artifactId = "custom-progress-compose"
                version = "1.0"
            }
        }
    }
}