import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "local"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data)

            implementation(libs.kotlin.stdlib)
            // Add KMP dependencies here

            // DataStore
            api(libs.datastore.preferences)
            api(libs.datastore)

            // coroutine
            implementation(libs.kotlinx.coroutines)
        }
    }
}

android {
    namespace = "com.party.guham2.local"
    compileSdk = 35
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
