package com.manugtdev.ghibliapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manugtdev.ghibliapp.domain.model.Film

@Entity
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",

    val description: String = "",
    val director: String = "",
    val image: String = "",
    //val locations: List<String> = emptyList(),
    val movie_banner: String = "",
    val original_title: String = "",
    val original_title_romanised: String = "",
    //val people: List<String> = emptyList(),
    val producer: String = "",
    val release_date: String = "",
    val rt_score: String = "",
    val running_time: String = "",
    //val species: List<String> = emptyList(),
    val title: String = "",
    val url: String = "",
    //val vehicles: List<String> = emptyList()
){
    fun toModel(): Film {
        return Film(
            id = id,
            description = description,
            director= director,
            image= image,
            movie_banner= movie_banner,
            original_title= original_title,
            original_title_romanised= original_title_romanised,
            producer= producer,
            release_date= release_date,
            rt_score= rt_score,
            running_time= running_time,
            title= title,
            url= url
        )
    }
}