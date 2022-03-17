package com.example.lockscreenusingjitpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lockscreenusingjitpackcompose.ui.screens.LockScreenHomePageBody
import com.example.lockscreenusingjitpackcompose.ui.screens.LockScreenPageBody
import com.example.lockscreenusingjitpackcompose.ui.viewmodels.MainViewModel

@Composable
fun LockScreenNavHost(navController : NavHostController, modifier : Modifier, viewModel : MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = LockScreenPages.LockScreenPage.name,
        modifier = modifier
    ) {
        composable(LockScreenPages.LockScreenPage.name) {
            LockScreenPageBody(onUnlocked = {navController.navigate(LockScreenPages.LockScreenHomePage.name)}, viewModel = viewModel)
        }
        composable(LockScreenPages.LockScreenHomePage.name) {
            LockScreenHomePageBody()
        }
    }
}