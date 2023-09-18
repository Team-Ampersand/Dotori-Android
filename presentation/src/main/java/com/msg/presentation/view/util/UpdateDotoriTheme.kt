package com.msg.presentation.view.util

import com.dotori.dotori_components.components.utils.Theme
import com.dotori.dotori_components.theme.DotoriTheme

fun DotoriTheme.updateDotoriTheme() {
    this.dotoriTheme = if (this.isSystemIsDarkTheme()) Theme.LIGHT else Theme.DARK
}