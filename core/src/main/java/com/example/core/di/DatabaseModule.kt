package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.MovieDao
import com.example.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext mContext: Context): MovieDatabase {
//        Room.databaseBuilder(mContext, MovieDatabase::class.java, "NontonKuy.db")
//                .fallbackToDestructiveMigration()
//                .openHelperFactory()
//                .build()
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("MovieApps".toCharArray())
        val factory = SupportFactory(passPhrase)
        val builderDB = Room.databaseBuilder(mContext, MovieDatabase::class.java, "NontonKuy.db")
        builderDB.fallbackToDestructiveMigration()
                .openHelperFactory(factory)
        return builderDB.build()
    }
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

}