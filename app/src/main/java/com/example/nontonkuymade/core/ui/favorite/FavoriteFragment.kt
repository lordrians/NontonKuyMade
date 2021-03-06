package com.example.nontonkuymade.core.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.ui.MovieAdapter
import com.example.nontonkuymade.core.ui.detail.DetailMovieActivity
import com.example.nontonkuymade.core.ui.detail.DetailMovieActivity.Companion.ID_MOVIE
import com.example.nontonkuymade.core.utils.setGridPixel
import com.example.nontonkuymade.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private val viewModel: MovieFavoriteViewModel by viewModels()

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

    }


    private fun loadData() {
        movieAdapter = MovieAdapter()
        binding.rvFavMovie.layoutManager = context?.let { setGridPixel(it) }?.let {GridLayoutManager(context,it)}
        binding.rvFavMovie.adapter = movieAdapter
        binding.rvFavMovie.setHasFixedSize(true)

        viewModel.favoriteMovie.observe(viewLifecycleOwner, Observer { favorites ->
            showProgressBar(false)
            movieAdapter.setData(favorites)
            movieAdapter.setOnItemClickCallback(this)
        })
    }

    private fun showProgressBar(state: Boolean) {
        binding.pbFavMov.isVisible = state
        binding.rvFavMovie.isInvisible = state
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(ID_MOVIE, data.id)
        context?.startActivity(intent)
    }

    override fun onResume() {
        loadData()
        super.onResume()
    }
}