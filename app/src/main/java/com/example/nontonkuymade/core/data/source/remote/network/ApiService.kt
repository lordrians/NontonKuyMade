package com.example.nontonkuymade.core.data.source.remote.network

import com.example.nontonkuymade.BuildConfig
import com.example.nontonkuymade.core.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuymade.core.data.source.remote.response.ResponseListMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("${BuildConfig.BASE_URL}movie/popular?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    suspend fun getAllMovie(): ResponseListMovie

    @GET("${BuildConfig.BASE_URL}movie/{idMovie}?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getDetailMovie(
        @Path("idMovie") idMovie: String?
    ): ResponseDetailMovie
}