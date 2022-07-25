package com.example.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.databinding.ItemMovieBinding
import com.example.core.domain.model.ModelMovie
import com.example.core.utils.helper



class MovieAdapter(private val listMovie:List<ModelMovie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding: ItemMovieBinding
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
     return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val movie = listMovie[position]
        binding.apply {
            ivPhoto.load(helper.IMAGE_PATH +movie.poster_path)
            tvTitle.text = movie.release_date
            tvMovieId.text = movie.title

            cardViewProfile.setOnClickListener{
                onItemClickCallback.onItemClicked(movie)
            }
        }
    }

    override fun getItemCount(): Int = listMovie.size


    interface OnItemClickCallback {
       fun onItemClicked(data:ModelMovie)
    }
}