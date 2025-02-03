import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(notation = libs.plugins.kotlinMultiplatform)
    alias(notation = libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(dependencyNotation = libs.kotlinx.coroutines)
        }
        androidMain.dependencies {
            // I might remove this later...
            implementation(dependencyNotation = libs.kotlinx.coroutines.android)
            implementation(dependencyNotation = libs.androidx.core)
            implementation(dependencyNotation = libs.bundles.koin)
            implementation(dependencyNotation = "com.arthenica:ffmpeg-kit-full:6.0-2")
        }
    }
}

android {
    namespace = "emmanuelmuturia.sonux.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}