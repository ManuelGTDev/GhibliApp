package com.manugtdev.ghibliapp.domain.repository

import com.manugtdev.ghibliapp.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    suspend fun getFilms(): List<Film>

    suspend fun getFilm(id: String): Film

    suspend fun insertFilmLocal(film: Film)

    fun getFilmsLocal(): Flow<List<Film>>

    suspend fun deleteLibraryFilm(film: Film)
}