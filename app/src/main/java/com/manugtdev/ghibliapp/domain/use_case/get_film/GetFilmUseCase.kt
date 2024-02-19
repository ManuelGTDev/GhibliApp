package com.manugtdev.ghibliapp.domain.use_case.get_film

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.manugtdev.ghibliapp.common.Resource
import com.manugtdev.ghibliapp.domain.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import com.manugtdev.ghibliapp.domain.repository.FilmRepository

class GetFilmUseCase @Inject constructor(
    private val repository: FilmRepository
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(id: String): Flow<Resource<Film>> = flow {

        try {
            emit(Resource.Loading())
            val film = repository.getFilm(id)
            emit(Resource.Success(film))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An internet error occurred, check your internet."
                )
            )
        }
    }

}