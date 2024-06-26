plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "gregor.developer.trainingprogramcompose"
    compileSdk = 34
    defaultConfig {
        applicationId = "gregor.developer.trainingprogramcompose"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    // Data Store
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    // Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.3")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // Dagger hilt
    implementation ("com.google.dagger:hilt-android:2.44.2")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    implementation("androidx.compose.material3:material3-android:1.2.1")
    implementation("androidx.compose.foundation:foundation-android:1.6.7")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    // Room
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")

    //Material
    implementation ("androidx.compose.material:material:1.2.1")

    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    //implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    implementation ("javax.annotation:javax.annotation-api:1.3.2")
}