import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.GradlePlugin.ANDROID_LIBRARY)
    id(ProjectProperties.GradlePlugin.KOTLIN_ANDROID)
    kotlin(ProjectProperties.GradlePlugin.KAPT)
}

android {
    namespace = "com.msg.data"
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

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

        buildConfigField(
            "String",
            "OEMBED_URL",
            getApiKey("OEMBED_URL")
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
    implementation(project(":domain"))

    implementation(Dependency.Androidx.CORE_KTX)
    implementation(Dependency.Androidx.APP_COMPAT)
    implementation(Dependency.Androidx.DATASTORE)

    implementation(Dependency.Google.MATERIAL)
    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)
    implementation(Dependency.Google.GSON)

    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

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
