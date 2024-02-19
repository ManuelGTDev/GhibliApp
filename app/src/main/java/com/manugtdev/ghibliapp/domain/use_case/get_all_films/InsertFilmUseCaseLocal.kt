package com.manugtdev.ghibliapp.domain.use_case.get_all_films

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.manugtdev.ghibliapp.common.Resource
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class InsertFilmUseCaseLocal @Inject constructor(
    private val repository: FilmRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(film: Film) = flow {

        try {
            val existingLocalFilms = mutableListOf<Film>()
            repository.getFilmsLocal().map { films ->
                existingLocalFilms.addAll(films)
            }

            //Primero comprobamos si la pelicula ya existe en la BD
            if (existingLocalFilms.any { it.id == film.id }) {
                emit(Resource.Error("The film is already in your library."))
            } else {
                emit(Resource.Loading())
                val films = repository.insertFilmLocal(film)
                emit(Resource.Success(films))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An internet error occurred."))
        }
    }
}