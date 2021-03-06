package com.example.nontonkuymade.core.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.nontonkuymade.BuildConfig
import com.example.nontonkuymade.R
import com.example.nontonkuymade.core.data.Resource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

//    @Inject
//    lateinit var factory: MovieViewModelFactory

    private val viewModel: DetailMovieViewModel by viewModels()

    private lateinit var binding: ActivityDetailMovieBinding
    private var idMovie: Int? = null

    companion object {
        const val ID_MOVIE = "id_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idMovie = intent?.getIntExtra(ID_MOVIE, 0)

        loadDetail()


    }

    private fun loadDetail() {

        viewModel.getDetailMovie(idMovie.toString()).observe(this, Observer { detail ->
            when (detail) {
                is Resource.Loading -> showProgressBar(true)
                is Resource.Success -> {
                    if (detail.data != null){
                        val state = detail.data.isFav
                        setupFavState(state)
                        showProgressBar(false)
                        fillingData(detail.data)
                    }
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun fillingData(data: MovieEntity) {
        val min = " min"
        with(binding){
            tvMovdetailTitle.text = data.originalTitle
            tvMovdetailGenre.text = data.genres
            tvMovdetailDate.text = data.releaseDate
            tvMovdetailDesc.text = data.overview
            tvMovdetailRuntime.text = data.runtime.toString() + min
            tvMovdetailBudget.text = data.budget.toString()
            tvMovdetailRevenue.text = data.revenue.toString()

            Glide.with(root.context)
                .load("${BuildConfig.PATH_ORIGINAL_IMG}${data.backdropPath}")
                .into(ivMovdetailBanner)

            Glide.with(root.context)
                .load("${BuildConfig.PATH_IMG}${data.posterPath}")
                .into(ivMovdetailPoster)

            fabFavorite.setOnClickListener { viewModel.setFavoriteMovie() }
        }

    }

    private fun setupFavState(state: Boolean) {
        if (state)
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_fill))
        else
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
    }


    private fun showProgressBar(state: Boolean){
        binding.pbMovieDetail.isVisible = state
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }
}