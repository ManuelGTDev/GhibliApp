package com.manugtdev.ghibliapp.domain.use_case.delete_library_film

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.manugtdev.ghibliapp.common.Resource
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteFilmUseCaseLocal @Inject constructor(
    private val repository: FilmRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(film: Film) = flow {

        try {
            emit(Resource.Loading())
            val films = repository.deleteLibraryFilm(film)
            emit(Resource.Success(films))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An internet error occurred."))
        }
    }
}