package com.manugtdev.ghibliapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manugtdev.ghibliapp.domain.entities.FilmEntity

@Database(
    entities = [FilmEntity::class],
    version = 1
)
abstract class FilmDatabase: RoomDatabase(){

    abstract val filmDao: FilmDao

}
