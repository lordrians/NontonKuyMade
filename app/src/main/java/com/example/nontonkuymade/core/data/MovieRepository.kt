package com.example.nontonkuymade.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nontonkuymade.core.data.source.local.MovieLocalDataSource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.data.source.remote.MovieRemoteDataSource
import com.example.nontonkuymade.core.data.source.remote.network.ApiResponse
import com.example.nontonkuymade.core.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuymade.core.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuymade.core.domain.repository.IMovieRepository
import com.example.nontonkuymade.core.utils.AppExecutors
import com.example.nontonkuymade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository private constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository{

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: MovieRemoteDataSource,
            localData: MovieLocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovie(): Flow<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<ResultsItemListMovie>>(appExecutors){
            override fun loadFromDB(): Flow<List<MovieEntity>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemListMovie>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResultsItemListMovie>) {
                val movies = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movies)
            }
        }.asFlow()
    }

    override fun getDetailMovie(idMovie: String): Flow<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, ResponseDetailMovie>(appExecutors){
            override fun loadFromDB(): Flow<MovieEntity> =
                localDataSource.getDetailMovie(idMovie)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.genres == ""

            override suspend fun createCall(): Flow<ApiResponse<ResponseDetailMovie>> =
                remoteDataSource.getDetailMovie(idMovie)

            override suspend fun saveCallResult(data: ResponseDetailMovie) {
                val movieDetail = DataMapper.mapResponseDetailToEntities(data)
                localDataSource.updateMovie(movieDetail,false)
            }
        }.asFlow()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavMovie(movie, state)
        }
    }

    override fun getFavMovie(): Flow<List<MovieEntity>> {
        return localDataSource.getFavMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
//
//    override fun getAllMovie(): LiveData<Resource<List<MovieEntity>>> =
//        object : NetworkBoundResource<List<MovieEntity>, List<ResultsItemListMovie>>(appExecutors){
//            override fun loadFromDB(): LiveData<List<MovieEntity>> {
//                return Transformations.map(localDataSource.getAllMovie()){
//                    DataMapper.mapEntitiesToDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
//                data == null || data.isEmpty()
//
//            override fun createCall(): LiveData<ApiResponse<List<ResultsItemListMovie>>> =
//                remoteDataSource.getAllMovie()
//
//            override fun saveCallResult(data: List<ResultsItemListMovie>) {
//                val movies = DataMapper.mapResponseToEntities(data)
//                localDataSource.insertMovie(movies)
//            }
//        }.asLiveData()
//
//
//    override fun getDetailMovie(idMovie: String): LiveData<Resource<MovieEntity>> =
//        object : NetworkBoundResource<MovieEntity, ResponseDetailMovie>(appExecutors){
//            override fun loadFromDB(): LiveData<MovieEntity> =
//                localDataSource.getDetailMovie(idMovie)
//
//            override fun shouldFetch(data: MovieEntity?): Boolean =
//                data != null && data.genres == ""
//
//            override fun createCall(): LiveData<ApiResponse<ResponseDetailMovie>> =
//                remoteDataSource.getDetailMovie(idMovie)
//
//            override fun saveCallResult(data: ResponseDetailMovie) {
//                val movieDetail = DataMapper.mapResponseDetailToEntities(data)
//                localDataSource.updateMovie(movieDetail, false)
//            }
//        }.asLiveData()
//
//    override fun setFavMovie(movie: MovieEntity, state: Boolean) {
//        appExecutors.diskIO().execute {
//            localDataSource.setFavMovie(movie, state)
//        }
//    }
//
//    override fun getFavMovie(): LiveData<Resource<List<MovieEntity>>> =
//        object : NetworkBoundResource<List<MovieEntity>, List<ResultsItemListMovie>>(appExecutors){
//            override fun loadFromDB(): LiveData<List<MovieEntity>> {
//                return Transformations.map(localDataSource.getFavMovies()){
//                    DataMapper.mapEntitiesToDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: List<MovieEntity>?): Boolean = false
//
//            override fun createCall(): LiveData<ApiResponse<List<ResultsItemListMovie>>> {
//                TODO("Not yet implemented")
//            }
//
//            override fun saveCallResult(data: List<ResultsItemListMovie>) {
//                TODO("Not yet implemented")
//            }
//
//        }.asLiveData()
}