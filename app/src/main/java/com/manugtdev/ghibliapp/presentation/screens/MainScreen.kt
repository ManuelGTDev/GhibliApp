package com.manugtdev.ghibliapp.presentation.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manugtdev.ghibliapp.presentation.components.BottonNavigationBar
import com.manugtdev.ghibliapp.presentation.navigation.Routes
import com.manugtdev.ghibliapp.presentation.screens.home.HomeScreen
import com.manugtdev.ghibliapp.presentation.screens.library.LibraryScreen
import com.manugtdev.ghibliapp.presentation.viewmodels.FilmsViewModel
import com.manugtdev.ghibliapp.presentation.viewmodels.LibraryViewModel


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainScreen(
    viewModel: FilmsViewModel,
    navigationControllerGeneral: NavHostController
) {
    val navigationControllerBottomBar = rememberNavController()

    Scaffold(
        bottomBar = { BottonNavigationBar(navigationControllerBottomBar) },
    ) {
        NavHost(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
            navController = navigationControllerBottomBar,
            startDestination = Routes.HomeScreen.route
        ) {
            composable(Routes.LibraryScreen.route) {
                val libraryViewModel: LibraryViewModel = hiltViewModel()
                LibraryScreen(navigationControllerGeneral, libraryViewModel)
            }
            composable(Routes.HomeScreen.route) {
                HomeScreen(viewModel, navigationControllerGeneral)
            }
        }
    }

}