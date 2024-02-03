package com.msg.presentation.view.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.dotoriClickable(
    onClick: () -> Unit
) = composed {
    combinedClickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    )
}