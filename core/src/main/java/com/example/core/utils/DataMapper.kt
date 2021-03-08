package com.example.core.utils

import com.example.core.data.source.remote.response.ResultsItemListMovie
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.ResponseDetailMovie

object DataMapper {

    fun mapResponseToEntities(input: List<ResultsItemListMovie>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                originalTitle = it.originalTitle,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                runtime = 0,
                genres = "",
                posterPath = it.posterPath,
                isFav = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<MovieEntity> =
        input.map {
            MovieEntity(
                id = it.id,
                originalTitle = it.originalTitle,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                runtime = it.runtime,
                genres = it.genres,
                posterPath = it.posterPath,
                isFav = it.isFav
            )
        }

    fun mapResponseDetailToEntities (
            input: ResponseDetailMovie
    ): MovieEntity {

        var genresAdded = ""
        for (i in input.genres?.indices!!){
            if (i < input.genres.size - 1)
                genresAdded += input.genres[i]?.name + "; "
            else
                genresAdded += input.genres[i]?.name
        }
        val detailMovie = MovieEntity (
                id = input.id,
                backdropPath = input.backdropPath,
                posterPath = input.posterPath,
                originalTitle = input.originalTitle,
                genres = genresAdded,
                voteAverage = input.voteAverage?.toDouble(),
                releaseDate = input.releaseDate,
                runtime = input.runtime,
                budget = input.budget,
                revenue = input.revenue,
                overview = input.overview

        )

        return detailMovie
    }
}