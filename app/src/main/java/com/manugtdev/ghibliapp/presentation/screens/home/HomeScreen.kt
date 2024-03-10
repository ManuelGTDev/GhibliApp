package com.manugtdev.ghibliapp.presentation.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.manugtdev.ghibliapp.R
import com.manugtdev.ghibliapp.presentation.components.LazyColumnFilms
import com.manugtdev.ghibliapp.presentation.components.SearchBarMain
import com.manugtdev.ghibliapp.presentation.navigation.Routes
import com.manugtdev.ghibliapp.presentation.ui.theme.Nunito
import com.manugtdev.ghibliapp.presentation.viewmodels.FilmsViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(viewModel: FilmsViewModel, navigationControllerGeneral: NavHostController) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val isDarkMode = isSystemInDarkTheme()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (!isDarkMode) Color(0xFFFFFFFF)
            else colorResource(id = R.color.almost_back))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Ghibli App",
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                color = if (isDarkMode) Color.White else Color.Black
            )
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            SearchBarMain(viewModel.searchText.value, onValueChange = {
                viewModel.onSearchTextChange(it)
            })
        }

        LazyColumnFilms(filmList = viewModel.state.value.films.filter {
            it.title.contains(searchText, ignoreCase = true)
        }, isLoading = state.isLoading) {
            navigationControllerGeneral.navigate(Routes.DetailScreen.route + "/${it.id}")
        }

    }
}