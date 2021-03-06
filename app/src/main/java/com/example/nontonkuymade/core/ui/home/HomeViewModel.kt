package com.example.nontonkuymade.core.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    movieUseCase: MovieUseCase
): ViewModel() {

    val movie = movieUseCase.getAllMovie().asLiveData()

}