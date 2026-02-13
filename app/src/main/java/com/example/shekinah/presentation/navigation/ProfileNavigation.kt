package com.example.shekinah.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shekinah.presentation.screen.profile.ProfileRout
import com.example.shekinah.presentation.screen.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.profileScreen(
    navigateTo: (Any) -> Unit
) {
    composable<ProfileScreenRout> {
        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.profileImageState.collectAsState()
        ProfileRout(
            navigateTo = navigateTo,
            state = state,
            onUploadImage = { uri ->
                viewModel.uploadProfileImage(uri)
            }
        )
    }
}