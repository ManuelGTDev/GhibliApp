package com.manugtdev.ghibliapp.presentation

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manugtdev.ghibliapp.presentation.navigation.Routes
import com.manugtdev.ghibliapp.presentation.screens.MainScreen
import com.manugtdev.ghibliapp.presentation.screens.detail.DetailScreen
import com.manugtdev.ghibliapp.presentation.screens.onboarding.OnBoardingScreen
import com.manugtdev.ghibliapp.presentation.ui.theme.GhibliAppTheme
import com.manugtdev.ghibliapp.presentation.viewmodels.DetailViewModel
import com.manugtdev.ghibliapp.presentation.viewmodels.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Text(text = "HOLA", Modifier.testTag("TEXTO1"))
            
            // Obtener SharedPreferences
            //Se puede usar DataStore pero por sencillez usamos sharedPreferences
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

            // Comprobar si es la primera vez que se abre la aplicación
            val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)


            GhibliAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navigationControllerGeneral = rememberNavController()

                    NavHost(
                        navController = navigationControllerGeneral,
                        startDestination = if (isFirstTime) Routes.OnboardingScreen.route
                        else Routes.MainScreen.route
                    ) {
                        composable(Routes.OnboardingScreen.route) {
                            // Guardar en SharedPreferences que ya se ha mostrado el onboarding
                            sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
                            OnBoardingScreen(navigationControllerGeneral)
                        }
                        composable(Routes.MainScreen.route) {
                            val filmsViewModel: FilmsViewModel = hiltViewModel()
                            MainScreen(viewModel = filmsViewModel, navigationControllerGeneral)
                        }
                        composable(
                            Routes.DetailScreen.route + "/{id}"
                        ) {
                            val detailViewModel: DetailViewModel = hiltViewModel()
                            DetailScreen(detailViewModel, navigationControllerGeneral)
                        }
                    }
                }
            }
        }
    }
}