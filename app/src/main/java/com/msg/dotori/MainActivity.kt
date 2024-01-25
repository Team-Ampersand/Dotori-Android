package com.msg.dotori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.view.home.navigation.mainRoute
import com.msg.presentation.view.login.navigation.loginRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DotoriTheme {
                DotoriNavHost(navController = rememberNavController(), startDestination = loginRoute)
            }
        }
    }
}