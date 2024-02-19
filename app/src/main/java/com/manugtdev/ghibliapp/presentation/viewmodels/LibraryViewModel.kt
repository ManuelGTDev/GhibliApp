package com.manugtdev.ghibliapp.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugtdev.ghibliapp.common.Resource
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.domain.use_case.delete_library_film.DeleteFilmUseCaseLocal
import com.manugtdev.ghibliapp.domain.use_case.get_all_films.GetFilmsUseCaseLocal
import com.manugtdev.ghibliapp.presentation.states.FilmState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getFilmsLocalUseCase: GetFilmsUseCaseLocal,
    private val deleteFilmUseCaseLocal: DeleteFilmUseCaseLocal
) : ViewModel() {

    private val _filmsState = mutableStateOf(FilmState())
    val filmsState = _filmsState

    init {
        showSavedFilms()
    }

    private fun showSavedFilms() {

        getFilmsLocalUseCase().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _filmsState.value = FilmState(films = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _filmsState.value =
                        FilmState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _filmsState.value = FilmState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

    fun deleteFilm(film: Film) {
        deleteFilmUseCaseLocal(film).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _filmsState.value =
                        _filmsState.value.copy(films = _filmsState.value.films - film)
                }

                is Resource.Error -> {
                    _filmsState.value =
                        FilmState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    //
                }
            }
        }.launchIn(viewModelScope)
    }
}