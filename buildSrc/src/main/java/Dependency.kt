object Dependency {
    object Androidx {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"
        const val VIEWMODEl = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.VIEWMODEL}"
        const val HILT_VIEWMODEL = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_VIEWMODEL}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    }

    object Libraries {
        const val DUS = "com.github.Team-Ampersand:DUS:${Versions.DUS}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    }

    object Compose {
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
        const val COMPOSE = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val COMPOSE_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_MATERIAL}"
        const val COMPOSE_TOOL = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE}"
        const val LANDSCAPIST_COMPOSE = "com.github.skydoves:landscapist-glide:${Versions.LANDSCAPIST_COMPOSE}"
    }

    object UnitTest {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

    object Msg {
        const val GAUTH = "com.github.GSM-MSG:GAuth-Signin-Android:v${Versions.GAUTH}"
    }
}
