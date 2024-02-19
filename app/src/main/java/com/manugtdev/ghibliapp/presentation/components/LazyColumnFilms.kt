package com.manugtdev.ghibliapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.manugtdev.ghibliapp.domain.model.Film

@Composable
fun LazyColumnFilms(
    filmList: List<Film>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Film) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            items(3) {
                ShimmerListItem()
            }
        } else {
            items(filmList) { film ->
                FilmItem(film = film, onItemClick = {
                    onClick(film)
                })
            }
        }
    }
}