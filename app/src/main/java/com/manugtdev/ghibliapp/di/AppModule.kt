package com.manugtdev.ghibliapp.di

import android.content.Context
import androidx.room.Room
import com.manugtdev.ghibliapp.common.Constants
import com.manugtdev.ghibliapp.data.local.FilmDao
import com.manugtdev.ghibliapp.data.local.FilmDatabase
import com.manugtdev.ghibliapp.data.remote.GhibliApi
import com.manugtdev.ghibliapp.data.repository.FilmRepositoryImpl
import com.manugtdev.ghibliapp.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGhibliApi(): GhibliApi {

        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GhibliApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmsDatabase(@ApplicationContext applicationContext: Context): FilmDatabase {
        return Room.databaseBuilder(
            applicationContext,
            FilmDatabase::class.java,
            "FilmsDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFilmDao(filmDatabase: FilmDatabase): FilmDao{
        return  filmDatabase.filmDao
    }


    @Provides
    @Singleton
    fun provideFilmRepository(api: GhibliApi, db: FilmDatabase): FilmRepository {
        return FilmRepositoryImpl(api, db.filmDao)
    }

}