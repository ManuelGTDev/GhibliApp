package com.manugtdev.ghibliapp.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugtdev.ghibliapp.common.Resource
import com.manugtdev.ghibliapp.domain.use_case.get_all_films.GetFilmsUseCase
import com.manugtdev.ghibliapp.presentation.states.FilmState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FilmState())
    val state = _state.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        getFilms()
    }

    private fun getFilms() {

        getFilmsUseCase().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = FilmState(films = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value =
                        FilmState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = FilmState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

}