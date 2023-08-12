plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.tugascatatanuas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tugascatatanuas"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.0")
    implementation("androidx.navigation:navigation-ui:2.7.0")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    implementation("com.google.android.gms:play-services-auth:20.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.core:core-ktx:1.10.1")
}