package com.example.favorite

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.ModelMovie
import com.example.core.ui.adapter.MovieAdapter
import com.example.core.utils.DataMapper
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.movieyuk.detailMovie.Detailmovie
import com.example.movieyuk.di.FavoriteModuleDependecies
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class Favorite : AppCompatActivity(), MovieAdapter.OnItemClickCallback {
    private lateinit var  binding : ActivityFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel:FavoriteViewModel by viewModels {
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        DataMapper.setToolbar(supportActionBar, titleName = "Favorite Movie ", subTitleName = "Antika Orinda")

        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(EntryPointAccessors.fromApplication(
                applicationContext,
                FavoriteModuleDependecies::class.java
            ))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        favoriteViewModel.getAllFavoriteMovies().observe(this){
            showFavoriteList(it)
        }
    }

    private fun showFavoriteList(listMovie:List<ModelMovie>){
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFavorite.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        }
        val listMovieSAdapter = MovieAdapter(listMovie)
        listMovieSAdapter.setOnItemClickCallback(this)
        binding.rvFavorite.adapter = listMovieSAdapter
    }

    override fun onItemClicked(data: ModelMovie) {
        startActivity(
            Intent(this, Detailmovie::class.java).apply {
                putExtra(Detailmovie.extra_movie,data)
            }
        )
    }
}