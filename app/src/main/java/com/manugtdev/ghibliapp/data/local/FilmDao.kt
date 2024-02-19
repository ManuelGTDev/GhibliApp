package com.manugtdev.ghibliapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manugtdev.ghibliapp.domain.entities.FilmEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmDao{
    @Query("SELECT * FROM FilmEntity")
    fun getFilms(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilm(film: FilmEntity)

    @Delete
    suspend fun deleteLibraryFilm(film: FilmEntity)
}