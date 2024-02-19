package com.manugtdev.ghibliapp.presentation.states

import com.manugtdev.ghibliapp.domain.model.Film

data class DetailState(

    val isLoading: Boolean = false,
    val film: Film = Film(),
    val error: String = ""
)