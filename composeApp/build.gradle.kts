import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlin.serializable)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            // logic + nav
            implementation(libs.decompose)

            // di
            implementation(libs.koin.android)

            // network
            implementation(libs.ktor.client.okhttp)

            // async
            implementation(libs.kotlin.coroutines.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)

            // logic + nav
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)

            // di
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            // network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // persistence
            implementation(libs.multiplatform.setting)

            // async
            implementation(libs.kotlin.coroutines.core)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.compose.jetbrains.expui.theme)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.slf4j.api)
            implementation(libs.slf4j.logger)
            implementation(libs.kotlin.coroutines.swing)
        }
    }
}

android {
    namespace = "ru.dezerom.otvechaylo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "ru.dezerom.otvechaylo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            modules("jdk.unsupported")

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.dezerom.otvechaylo"
            packageVersion = "1.0.0"
        }
    }
}
