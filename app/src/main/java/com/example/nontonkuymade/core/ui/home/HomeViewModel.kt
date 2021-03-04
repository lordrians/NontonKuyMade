package com.example.nontonkuymade.core.ui.home

import androidx.lifecycle.ViewModel
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase

class HomeViewModel(
    movieUseCase: MovieUseCase
): ViewModel() {

    val movie = movieUseCase.getAllMovie()

}