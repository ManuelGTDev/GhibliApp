package com.manugtdev.ghibliapp.data.repository

import com.manugtdev.ghibliapp.data.local.FilmDao
import com.manugtdev.ghibliapp.data.remote.GhibliApi
import com.manugtdev.ghibliapp.data.remote.dto.toModel
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRepositoryImpl @Inject constructor(
    private val api: GhibliApi,
    private val daoFilm: FilmDao
) : FilmRepository {

    //REMOTE
    override suspend fun getFilms(): List<Film> {
        val filmList = api.getAllFilms()
        return filmList.map { it.toModel() }
    }

    override suspend fun getFilm(id: String): Film {

        val film = api.getFilm(id)
        return film.toModel()
    }

    //LOCAL
    override suspend fun insertFilmLocal(film: Film) {
        return daoFilm.insertFilm(film.toEntity())
    }

    override fun getFilmsLocal(): Flow<List<Film>> {
        return daoFilm.getFilms().map { filmEntityList ->
            filmEntityList.map { it.toModel() } }
    }

    override suspend fun deleteLibraryFilm(film: Film) {
        return daoFilm.deleteLibraryFilm(film.toEntity())
    }
}

