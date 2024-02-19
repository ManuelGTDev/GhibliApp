package com.manugtdev.ghibliapp.presentation.navigation

sealed class Routes(val route: String) {

    object OnboardingScreen: Routes("onboardingScreen")
    object HomeScreen: Routes("homeScreen")
    object MainScreen: Routes("mainScreen")
    object SearchScreen: Routes("searchScreen")
    object LibraryScreen: Routes("libraryScreen")
    object DetailScreen: Routes("detailScreen")
}