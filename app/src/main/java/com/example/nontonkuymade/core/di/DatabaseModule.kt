package com.example.nontonkuymade.core.di

import android.content.Context
import androidx.room.Room
import com.example.nontonkuymade.core.data.source.local.room.MovieDao
import com.example.nontonkuymade.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(mContext: Context): MovieDatabase =
        Room.databaseBuilder(mContext, MovieDatabase::class.java, "NontonKuy.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

}