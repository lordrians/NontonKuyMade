package com.example.nontonkuymade.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase

class HomeViewModel @ViewModelInject constructor(
    movieUseCase: MovieUseCase
): ViewModel() {

    val movie = movieUseCase.getAllMovie().asLiveData()
//ini view model
}