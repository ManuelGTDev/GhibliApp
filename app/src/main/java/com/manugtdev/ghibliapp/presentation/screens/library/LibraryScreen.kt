package com.manugtdev.ghibliapp.presentation.screens.library

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.manugtdev.ghibliapp.presentation.components.FilmItemLibrary
import com.manugtdev.ghibliapp.presentation.navigation.Routes
import com.manugtdev.ghibliapp.presentation.ui.theme.Nunito
import com.manugtdev.ghibliapp.presentation.viewmodels.LibraryViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun LibraryScreen(
    navigationControllerBottomBar: NavHostController,
    viewModel: LibraryViewModel
) {
    val films = viewModel.filmsState.value
    val isDarkMode = isSystemInDarkTheme()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            text = "My Saved Films",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            color = if (isDarkMode) Color.White else Color.Black
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(films.films) { film ->
                FilmItemLibrary(film = film, viewModel) { filmitem ->
                    navigationControllerBottomBar.navigate(Routes.DetailScreen.route + "/${filmitem.id}")
                }
            }
        }
    }

}