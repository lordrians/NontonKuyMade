package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.ResponseDetailMovie
import com.example.core.data.source.remote.response.ResultsItemListMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){
//    companion object {
//        @Volatile
//        private var instance: MovieRemoteDataSource? = null
//
//        fun getInstance(service: ApiService): MovieRemoteDataSource =
//            instance ?: synchronized(this){
//                instance ?: MovieRemoteDataSource(service)
//            }
//    }

    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsItemListMovie>>> {
        return flow {
            try {
                val response = apiService.getAllMovie()
                val dataMovies = response.results
                if (dataMovies != null) {
                    if (dataMovies.isNotEmpty())
                        emit(ApiResponse.Success(dataMovies))
                    else
                        emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(idMovie: String): Flow<ApiResponse<ResponseDetailMovie>>{

        return flow {
            try {
                val response = apiService.getDetailMovie(idMovie)
                val detailMovie = response
                if (detailMovie != null)
                    emit(ApiResponse.Success(detailMovie))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}