package com.example.movieyuk.detailMovie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import coil.load
import com.example.movieyuk.R
import com.example.movieyuk.databinding.ActivityDetailMovieBinding
import com.example.core.domain.model.ModelMovie
import com.example.core.utils.DataMapper
import com.example.core.utils.FormatContent
import com.example.core.utils.helper
import com.example.core.utils.helper.EXTRA_MOVIE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detailmovie : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMovieBinding
    private var isFavorite = false

    private val detailViewModel by viewModels<DetailViewModel>()

    companion object{
        const val extra_movie = EXTRA_MOVIE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        DataMapper.setToolbar(supportActionBar, titleName = "Detail Movie ", subTitleName = "Antika Orinda")
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeMovie()

        detailViewModel.getDetailMovies().observe(this){
            showDetailMovie()
        }
        binding.progressBar2.isVisible = true
    }



    private fun showDetailMovie(){
        binding.progressBar2.isVisible = false
        intent.getParcelableExtra<ModelMovie>(extra_movie)?.let { modelMovie ->
            binding.apply {
                tvTitleMovie.text = modelMovie.title
                tvReleaseDate.text = FormatContent.ParsingFormatDate(modelMovie.release_date)
                tvPopularity.text = modelMovie.popularity.toString()
                tvOverview.text = modelMovie.overview

                imageView.load(helper.IMAGE_PATH +modelMovie.poster_path)

               detailViewModel.isFavoriteMovie(modelMovie.title)

                favoriteAdd.setOnClickListener {
                    if(!isFavorite) detailViewModel.insertMovieToFavorite(modelMovie)
                    else detailViewModel.removedMovieToFavorite(modelMovie)
                }
            }
        }
    }

    private fun observeMovie(){
        detailViewModel.isMovieFavorite.observe(this@Detailmovie){
            isFavorite = it
            binding.favoriteAdd.setFavorite(it,this@Detailmovie)
        }
    }

    private fun FloatingActionButton.setFavorite(isMovieFavorite:Boolean,context: Context) {
        if (isMovieFavorite) {
            this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite))
        } else {
            this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_not_favorite))
        }
    }
}