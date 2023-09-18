import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.GradlePlugin.ANDROID_APPLICATION)
    id(ProjectProperties.GradlePlugin.KOTLIN_ANDROID)
    id(ProjectProperties.GradlePlugin.HILT_PLUGIN)
    kotlin(ProjectProperties.GradlePlugin.KAPT)
}

android {
    namespace = "com.msg.dotori"
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.msg.dotori"
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "RELEASE_URL",
            getApiKey("RELEASE_URL")
        )

        buildConfigField(
            "String",
            "DEVELOP_URL",
            getApiKey("DEVELOP_URL")
        )
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
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependency.Androidx.CORE_KTX)
    implementation(Dependency.Androidx.APP_COMPAT)

    implementation(Dependency.Google.MATERIAL)
    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.Compose.NAVIGATION_COMPOSE)

    testImplementation(Dependency.UnitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}
