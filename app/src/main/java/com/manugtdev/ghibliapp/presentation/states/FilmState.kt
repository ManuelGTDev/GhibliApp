package com.manugtdev.ghibliapp.presentation.states

import com.manugtdev.ghibliapp.domain.model.Film

data class FilmState(
    val isLoading: Boolean = false,
    val films: List<Film> = emptyList(),
    val error: String = ""
)