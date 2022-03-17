package com.example.lockscreenusingjitpackcompose.navigation

import androidx.compose.runtime.Composable
import com.example.lockscreenusingjitpackcompose.ui.screens.LockScreenHomePageBody
import com.example.lockscreenusingjitpackcompose.ui.screens.LockScreenPageBody

/**
 * Screen metadata for LockScreen.
 */
enum class LockScreenPages(
    val body: @Composable ((String) -> Unit) -> Unit,
) {
    LockScreenPage(
        body = { LockScreenPageBody() }
    ),
    LockScreenHomePage(
        body = { LockScreenHomePageBody()}
    );

    companion object {
        fun fromRoute(route: String?): LockScreenPages =
            when (route?.substringBefore("/")) {
                LockScreenPage.name -> LockScreenPage
                LockScreenHomePage.name -> LockScreenHomePage
                null -> LockScreenPage
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
