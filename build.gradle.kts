buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Build.androidBuildToolApplication) version Build.androidBuildToolsVersion apply false
    id(Build.androidBuildToolLibrary) version Build.androidBuildToolsVersion apply false
    id(Build.kotlinGradleAndroid) version Kotlin.AndroidVersion apply false
    id(Build.kotlinGradleJvm) version Kotlin.JvmVersion apply false
    id(Build.hiltAndroidGradlePlugin) version Build.hiltAndroidGradlePluginVersion apply false
}