package com.example.challangechapter5

import android.graphics.Movie
import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.challangechapter5.databinding.ActivityDetailBinding
import com.example.challangechapter5.model.Result
import com.example.challangechapter5.model.movieApi
import com.example.challangechapter5.network.RetrofitClient
import com.example.challangechapter5.viewmodel.MovieViewModel
import com.google.android.play.integrity.internal.m
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewModel
    lateinit var binding: ActivityDetailBinding
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var getDetail = intent.getIntExtra("id", -1)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.liveDetail.observe(this){movie ->
            bindMovieData(movie)

        }
            viewModel.getMovieDetail(getDetail)


    }
    private fun bindMovieData(movie: com.example.challangechapter5.model.Result) {
        if (movie != null) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(imageView2)

                judul.text = movie.title
                Tanggal.text = movie.releaseDate
                Deskripsi.text = movie.overview
            }
        }
    }
}