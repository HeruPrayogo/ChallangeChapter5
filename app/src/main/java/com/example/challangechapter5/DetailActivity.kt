package com.example.challangechapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.view.menu.MenuView.ItemView
import com.bumptech.glide.Glide
import com.example.challangechapter5.databinding.ActivityDetailBinding
import com.example.challangechapter5.model.Result

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getDetail = intent.getSerializableExtra("detailMovie") as com.example.challangechapter5.model.Result
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${getDetail.posterPath}").into(binding.imageView2)
        binding.judul.text = getDetail.originalTitle
        binding.Tanggal.text = getDetail.releaseDate
        binding.Deskripsi.text = getDetail.overview
    }
}