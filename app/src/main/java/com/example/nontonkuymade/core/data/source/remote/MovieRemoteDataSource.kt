package com.example.nontonkuymade.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nontonkuymade.core.data.source.remote.network.ApiResponse
import com.example.nontonkuymade.core.data.source.remote.network.ApiService
import com.example.nontonkuymade.core.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuymade.core.data.source.remote.response.ResponseListMovie
import com.example.nontonkuymade.core.data.source.remote.response.ResultsItemListMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSource private constructor(
    private val apiService: ApiService
){
    companion object {
        @Volatile
        private var instance: MovieRemoteDataSource? = null

        fun getInstance(service: ApiService): MovieRemoteDataSource =
            instance ?: synchronized(this){
                instance ?: MovieRemoteDataSource(service)
            }
    }

    fun getAllMovie(): LiveData<ApiResponse<List<ResultsItemListMovie>>> {
        val resultData = MutableLiveData<ApiResponse<List<ResultsItemListMovie>>>()
        val client = apiService.getAllMovie()

        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseListMovie>,
                response: Response<ResponseListMovie>
            ) {
                resultData.value = ApiResponse.Success(response.body()?.results as List<ResultsItemListMovie>)
            }
        })
        return resultData
    }

    fun getDetailMovie(idMovie: String): LiveData<ApiResponse<ResponseDetailMovie>>{
        val resultData = MutableLiveData<ApiResponse<ResponseDetailMovie>>()
        val client = apiService.getDetailMovie(idMovie)

        client.enqueue(object : Callback<ResponseDetailMovie>{
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseDetailMovie>,
                response: Response<ResponseDetailMovie>
            ) {
                resultData.value = ApiResponse.Success(response.body() as ResponseDetailMovie)
            }
        })
        return resultData
    }
}