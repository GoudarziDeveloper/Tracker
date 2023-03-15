plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            @Suppress("UnstableApiUsage")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions.resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
}

dependencies {
    androidTestImplementation(Testing.espresso)
    //implementation("androidx.core:core-ktx:1.8.0")
    //implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    //implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntime)
    implementation(Compose.activityCompose)
    //implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform(Compose.composeBOM))
    //implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    //implementation("androidx.compose.ui:ui")
    implementation(Compose.ui)
    //implementation("androidx.compose.ui:ui-graphics")
    implementation(Compose.graphics)
    //implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(Compose.uiToolingPreview)
    //implementation("androidx.compose.material3:material3")
    implementation(Compose.material)
    //testImplementation("junit:junit:4.13.2")
    //testImplementation(Testing.junit4)
    //androidTestImplementation("androidx.test.ext:junit:1.1.5")
    //androidTestImplementation(Testing.junitAndroidExt)
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //androidTestImplementation(Testing.espresso)
    //androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation(platform(Testing.composeBOM))
    //androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(Testing.composeUiTest)

    implementation(Compose.navigation)
    implementation(Compose.hiltNavigationCompose)

    //debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation(Debug.composeUI)
    //debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation(Debug.composeUiTestManifest)

/*
    implementation(Compose.compiler)
    implementation(Compose.runtime)
    implementation(Compose.viewModelCompose)
    */

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.onboardingPresentation))
    implementation(project(Modules.onboardingDomain))
    implementation(project(Modules.trackerPresentation))
    implementation(project(Modules.trackerDomain))
    implementation(project(Modules.trackerData))


    //implementation(AndroidX.appCompat)

    implementation(Coil.coilCompose)

    implementation(Google.material)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

/*

    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
*/

}