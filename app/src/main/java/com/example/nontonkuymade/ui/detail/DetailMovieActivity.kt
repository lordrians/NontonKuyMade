package com.example.nontonkuymade.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.core.BuildConfig
import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.R
import com.example.nontonkuymade.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {


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
                        val state = detail.data!!.isFav
                        setupFavState(state)
                        showProgressBar(false)
                        fillingData(detail.data!!)
                    }
                }
                is Resource.Error -> {
                    showProgressBar(false)
                    Toast.makeText(applicationContext, detail.message.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        })
    }

    private fun fillingData(data: MovieEntity) {
        with(binding){
            tvMovdetailTitle.text = data.originalTitle
            tvMovdetailGenre.text = data.genres
            tvMovdetailDate.text = data.releaseDate
            tvMovdetailDesc.text = data.overview
            val runtime = StringBuilder(data.runtime.toString())
            tvMovdetailRuntime.text = runtime.append(" min")
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