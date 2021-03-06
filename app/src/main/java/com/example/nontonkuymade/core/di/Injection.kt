package com.example.nontonkuymade.core.di

import android.content.Context
import com.example.nontonkuymade.core.data.MovieRepository
import com.example.nontonkuymade.core.data.source.local.MovieLocalDataSource
import com.example.nontonkuymade.core.data.source.local.room.MovieDatabase
import com.example.nontonkuymade.core.data.source.remote.MovieRemoteDataSource
import com.example.nontonkuymade.core.data.source.remote.network.ApiConfig
import com.example.nontonkuymade.core.domain.repository.IMovieRepository
import com.example.nontonkuymade.core.domain.usecase.MovieInteractor
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase
import com.example.nontonkuymade.core.utils.AppExecutors

//object MovieInjection {
//    private fun provideRepository(mContext: Context): IMovieRepository {
//        val database = MovieDatabase.getInstance(mContext)
//
//        val remoteDataSource = MovieRemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = MovieLocalDataSource.getInstance(database.movieDao())
//        val appExecutors = AppExecutors()
//
//        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors )
//    }
//
//    fun provideMovieUseCase(mContext: Context): MovieUseCase{
//        val repository = provideRepository(mContext)
//        return MovieInteractor(repository)
//    }
//}