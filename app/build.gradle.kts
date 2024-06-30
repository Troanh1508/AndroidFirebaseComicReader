plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.androidfirebasecomicreader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidfirebasecomicreader"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // https://mvnrepository.com/artifact/com.github.chrisbanes/PhotoView
    implementation("com.github.chrisbanes:PhotoView:2.1.0")
    // https://mvnrepository.com/artifact/com.squareup.picasso/picasso
    implementation("com.squareup.picasso:picasso:2.71828")
    // https://mvnrepository.com/artifact/com.wajahatkarim/easyflipviewpager
    implementation("com.wajahatkarim:easyflipviewpager:2.0.1")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")






}