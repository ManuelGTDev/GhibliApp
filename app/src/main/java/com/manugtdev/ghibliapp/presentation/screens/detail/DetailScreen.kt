package com.manugtdev.ghibliapp.presentation.screens.detail

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.manugtdev.ghibliapp.presentation.components.TopAppNavBar
import com.manugtdev.ghibliapp.presentation.ui.theme.Nunito
import com.manugtdev.ghibliapp.presentation.viewmodels.DetailViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailScreen(viewModel: DetailViewModel, navController: NavController) {

    val state = viewModel.state.value
    val scrollState = rememberScrollState()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    Column(
        Modifier
            .verticalScroll(scrollState, enabled = true)
            .fillMaxSize()
    ) {

        TopAppNavBar(navController,
            filmName = state.film.title,
            isFavorite = isFavorite,
            onClick = {
                viewModel.addFilm(state.film)
                viewModel.toggleFavorites()
            })

        AsyncImage(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentScale = ContentScale.Crop,
            model = state.film.movie_banner, contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = state.film.title,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(top = 5.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = state.film.description,
                fontFamily = Nunito,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            FilmData(Icons.Default.ChevronRight, "Original Title: " + state.film.original_title)

            FilmData(
                Icons.Default.ChevronRight,
                "Romanised Title: " + state.film.original_title_romanised
            )

            FilmData(Icons.Default.AccountCircle, "Director: " + state.film.director)

            FilmData(Icons.Default.StarBorder, "Rating: " + state.film.rt_score)

            FilmData(
                Icons.Default.AccessTime,
                "Running Time: " + state.film.running_time + " minutes"
            )
        }
    }
}

@Composable
fun FilmData(icon: ImageVector, data: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Icon(
            modifier = Modifier.size(26.dp),
            imageVector = icon,
            contentDescription = "Author"
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            textAlign = TextAlign.Center,
            text = data,
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold
        )
    }
}




