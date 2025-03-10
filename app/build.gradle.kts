import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.jagteshwar.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jagteshwar.newsapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val localProperties = Properties()
    val localPropertiesFile = File(rootDir,"secret.properties")

    if(localPropertiesFile.exists() && localPropertiesFile.isFile){
        localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", localProperties.getProperty("API_KEY"))
        }
        debug {
            buildConfigField("String", "API_KEY", localProperties.getProperty("API_KEY"))
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
        buildConfig = true
        resValues = true
        android.buildFeatures.buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.core:core-splashscreen:1.0.1")
    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta")
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:3.2.0-rc01")
    val roomVersion = "2.5.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:2.5.2")
}