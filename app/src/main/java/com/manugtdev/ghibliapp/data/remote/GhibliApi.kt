package com.manugtdev.ghibliapp.data.remote

import com.manugtdev.ghibliapp.data.remote.dto.FilmDetailDto
import com.manugtdev.ghibliapp.data.remote.dto.FilmDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GhibliApi {

    //Get all films
    @GET("/films")
    suspend fun getAllFilms(): List<FilmDto>


    //Get one film by ID
    @GET("/films/{id}")
    suspend fun getFilm(@Path("id") id: String): FilmDetailDto

}