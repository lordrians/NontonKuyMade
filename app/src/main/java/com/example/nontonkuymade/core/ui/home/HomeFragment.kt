package com.example.nontonkuymade.core.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuymade.R
import com.example.nontonkuymade.core.data.Resource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.ui.MovieAdapter
import com.example.nontonkuymade.core.ui.detail.DetailMovieActivity
import com.example.nontonkuymade.core.ui.detail.DetailMovieActivity.Companion.ID_MOVIE
import com.example.nontonkuymade.core.utils.setGridPixel
import com.example.nontonkuymade.core.utils.setVisible
import com.example.nontonkuymade.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() , MovieAdapter.OnItemClickCallback{

//    @Inject
//    lateinit var factory: MovieViewModelFactory

    private val viewModelMovie: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

    }

    private fun loadData() {
        val adapter = MovieAdapter()
        binding.rvMovie.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context,it) }
        binding.rvMovie.adapter = adapter
        binding.rvMovie.setHasFixedSize(true)

        viewModelMovie.movie.observe(viewLifecycleOwner, Observer { movies ->
            when (movies){
                is Resource.Loading -> showProgressBar(true)
                is Resource.Success -> {
                    showProgressBar(false)
                    adapter.setData(movies.data)
                    adapter.setOnItemClickCallback(this)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    showProgressBar(false)
                    setVisible(binding.ivNodata)
                    Toast.makeText(context, resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()

                }
            }
        })
    }


    private fun showProgressBar(state: Boolean){
        binding.pbMovie.isVisible = state
        binding.rvMovie.isInvisible = state
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(ID_MOVIE, data.id)
        context?.startActivity(intent)
    }
}
