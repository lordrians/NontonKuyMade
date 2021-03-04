package com.example.nontonkuymade.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nontonkuymade.BuildConfig
import com.example.nontonkuymade.R
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.databinding.ItemGridBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private var listData = ArrayList<MovieEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(newListData: List<MovieEntity>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = this.listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listData[position])

    inner class ViewHolder (
        private val binding: ItemGridBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(movie: MovieEntity) {
            with(binding){
                tvItemgridDate.text = movie.releaseDate
                tvItemgridRate.text = movie.voteAverage.toString()
                tvItemgridTitle.text = movie.originalTitle
                Glide.with(root.context)
                    .load("${BuildConfig.PATH_IMG}${movie.posterPath}")
                    .into(ivItemgrid)

                root.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }

}