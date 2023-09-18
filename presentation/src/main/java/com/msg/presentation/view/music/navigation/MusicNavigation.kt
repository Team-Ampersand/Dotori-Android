package com.msg.presentation.view.music.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.music.MusicScreen

const val musicRoute = "music_route"

fun NavController.navigateToMusic() {
    this.navigate(musicRoute)
}

fun NavGraphBuilder.musicScreen() {
    composable(musicRoute) {
        MusicScreen()
    }
}