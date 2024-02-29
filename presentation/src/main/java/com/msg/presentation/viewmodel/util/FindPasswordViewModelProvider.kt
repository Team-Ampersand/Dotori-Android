package com.msg.presentation.viewmodel.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.msg.presentation.viewmodel.FindPasswordViewModel

@Composable
fun FindPasswordViewModelProvider(
    viewModelStoreOwner: ViewModelStoreOwner,
    content: @Composable (viewModel: FindPasswordViewModel) -> Unit
) {
    val viewModel: FindPasswordViewModel = hiltViewModel(viewModelStoreOwner)
    content(viewModel)
}