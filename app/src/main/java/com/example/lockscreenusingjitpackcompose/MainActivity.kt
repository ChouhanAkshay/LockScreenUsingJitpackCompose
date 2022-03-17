package com.example.lockscreenusingjitpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.lockscreenusingjitpackcompose.navigation.LockScreenNavHost
import com.example.lockscreenusingjitpackcompose.ui.theme.LockScreenUsingJitpackComposeTheme
import com.example.lockscreenusingjitpackcompose.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel by viewModels<MainViewModel>()
        setContent {
            LockScreenUsingJitpackComposeTheme {
                setContent {
                    LockScreenApp(viewmodel)
                }
            }
        }
    }
}

@Composable
fun LockScreenApp(viewModel: MainViewModel) {
    LockScreenUsingJitpackComposeTheme() {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            LockScreenNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
        }
    }
}

//todo remove hardcoded values
//todo add unit test cases
//todo add description for navigation, animation, and unit test cases