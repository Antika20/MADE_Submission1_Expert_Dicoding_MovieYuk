package com.example.movieyuk.home

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.source.remote.network.ApiResponse
import com.example.movieyuk.R
import com.example.movieyuk.databinding.ActivityMainBinding
import com.example.core.domain.model.ModelMovie
import com.example.core.ui.adapter.MovieAdapter
import com.example.core.utils.DataMapper
import com.example.core.utils.helper
import com.example.movieyuk.detailMovie.Detailmovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickCallback {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        DataMapper.setToolbar(supportActionBar, titleName = "Movie Yuk  ", subTitleName = "Antika Orinda")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvMovie.setHasFixedSize(true)

        mainViewModel.moviePlayingNow.observe(this){
            showRecylerListMovie(it)
        }

//        binding.btnSearch.setOnClickListener {
//           val textInputUser = binding.tillSearch.editText?.text.toString()
//            if (textInputUser.isNotEmpty()){
//                mainViewModel.searchMovieNow(textInputUser).observe(this,this::showRecylerListMovie)
//            }
//          else{
//                mainViewModel.moviePlayingNow.observe(this){
//                    showRecylerListMovie(it)
//                }
//            }
//        }

        observedDataAll()
        binding.edtQuery.addTextChangedListener {
            val textTemp = it.toString()
            if (textTemp.isEmpty()){
                observedDataAll()
            }
            else{
                mainViewModel.searchMovieNow(textTemp).observe(this,this::showRecylerListMovie)
            }
        }
            // Jika Ingin di Klik
//        binding.btnSearch.setOnClickListener {
//            val textInputUser = binding.tillSearch.editText?.text.toString()
//
//        }

//        binding.edtQuery.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_NEXT){
//                searchMovie(mainViewModel)
//            }
//            true
//        }
    }

    private fun observedDataAll(){
        mainViewModel.moviePlayingNow.observe(this){
            showRecylerListMovie(it)
        }
    }

    private fun showRecylerListMovie(listMovie: ApiResponse<List<ModelMovie>>){
        Log.d("token", "showRecylerListMovie:$listMovie ")
        binding.progressBar.isVisible = false
        when(listMovie) {
            is ApiResponse.Loading ->  binding.progressBar.visibility = View.VISIBLE
            is ApiResponse.Success ->  {
                binding.progressBar.visibility = View.GONE
                if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                else  binding.rvMovie.layoutManager = LinearLayoutManager(this)
                val listMovieAdapter = MovieAdapter(listMovie.data)
                listMovieAdapter.setOnItemClickCallback(this)
                binding.rvMovie.adapter =listMovieAdapter
            }
            is ApiResponse.Error -> {
                listMovie.errorMessage
                binding.progressBar.visibility = View.INVISIBLE
            }
            is ApiResponse.Empty -> {
                helper.EXTRA_NoFilm
                binding.progressBar.visibility = View.GONE
            }
        }
    }

//    private fun searchMovie(mainViewModel: MainViewModel){
//        binding.progressBar.isVisible = true
//        mainViewModel.searchMovieNow(binding.edtQuery.text.toString())
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite ->{
                toFavoriteDetail()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toFavoriteDetail(){
        val uri = Uri.parse("movieyuk://favorite")
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }

    override fun onItemClicked(data: ModelMovie) {
        startActivity(
            Intent(this@MainActivity, Detailmovie::class.java).apply {
                putExtra(Detailmovie.extra_movie,data)
            }
        )
    }
}