import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.GradlePlugin.ANDROID_LIBRARY)
    id(ProjectProperties.GradlePlugin.KOTLIN_ANDROID)
    id(ProjectProperties.GradlePlugin.HILT_PLUGIN)
    kotlin(ProjectProperties.GradlePlugin.KAPT)
}

android {
    namespace = "com.msg.presentation"
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
}

dependencies {
    implementation(project(":domain"))

    // okhttp
    implementation(Dependency.Libraries.OKHTTP)

    implementation(Dependency.Androidx.CORE_KTX)
    implementation(Dependency.Androidx.APP_COMPAT)
    implementation(Dependency.Androidx.VIEWMODEl)
    implementation(Dependency.Androidx.HILT_VIEWMODEL)

    implementation(Dependency.Google.MATERIAL)
    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    implementation(Dependency.Libraries.DUS)

    implementation(Dependency.Compose.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_TOOL)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_PREVIEW)
    implementation(Dependency.Compose.NAVIGATION_COMPOSE)
    implementation(Dependency.Compose.LANDSCAPIST_COMPOSE)

    implementation(Dependency.Msg.GAUTH)

    testImplementation(Dependency.UnitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}
