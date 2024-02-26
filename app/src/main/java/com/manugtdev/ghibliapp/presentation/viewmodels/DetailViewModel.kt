package com.manugtdev.ghibliapp.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugtdev.ghibliapp.common.Constants
import com.manugtdev.ghibliapp.common.Resource
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.domain.use_case.get_all_films.GetFilmsUseCaseLocal
import com.manugtdev.ghibliapp.domain.use_case.get_all_films.InsertFilmUseCaseLocal
import com.manugtdev.ghibliapp.domain.use_case.get_film.GetFilmUseCase
import com.manugtdev.ghibliapp.presentation.states.DetailState
import com.manugtdev.ghibliapp.presentation.states.FilmState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase,
    private val insertFilmUseCaseLocal: InsertFilmUseCaseLocal,
    private val getFilmsUseCaseLocal: GetFilmsUseCaseLocal,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    private val _filmsState = mutableStateOf(FilmState())
    val filmsState = _filmsState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private val _filmId = MutableStateFlow("")

    init {
        savedStateHandle.get<String>(Constants.FILM_ID)?.let { id ->
            getFilm(id)
            _filmId.value = id
        }
    }

    private fun getFilm(id: String) {
        getFilmUseCase(id).onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = DetailState(film = result.data ?: Film())
                    checkFavoriteFilms()
                }

                is Resource.Error -> {
                    _state.value =
                        DetailState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = DetailState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }


    fun addFilm(film: Film) {
        insertFilmUseCaseLocal(film).launchIn(viewModelScope)
    }

    private fun checkFavoriteFilms() {
        getFilmsUseCaseLocal().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _filmsState.value = FilmState(films = result.data ?: emptyList())
                    _filmsState.value.films.find { _state.value.film.id == _filmId.value }?.apply {
                        _isFavorite.value = true
                    }.run { _isFavorite.value = false }
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

    fun toggleFavorites(){
        _isFavorite.value = !_isFavorite.value
    }


}

