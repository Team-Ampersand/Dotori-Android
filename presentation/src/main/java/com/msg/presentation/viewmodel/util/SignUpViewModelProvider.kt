package com.msg.presentation.viewmodel.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.msg.presentation.viewmodel.SignUpViewModel

@Composable
fun SignUpViewModelProvider(
    viewModelStoreOwner: ViewModelStoreOwner,
    content: @Composable (viewModel: SignUpViewModel) -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel(viewModelStoreOwner)
    content(viewModel)
}