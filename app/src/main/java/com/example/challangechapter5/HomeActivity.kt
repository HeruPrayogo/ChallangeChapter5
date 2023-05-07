package com.example.challangechapter5

import android.content.Intent
import android.content.SharedPreferences
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangechapter5.databinding.ActivityHomeBinding
import com.example.challangechapter5.model.Result
import com.example.challangechapter5.viewmodel.MovieViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        sharedPreferences = getSharedPreferences("InsertAcc", MODE_PRIVATE)
        var getUss = sharedPreferences.getString("uss", "")
        binding.welcome.text ="Welcom, $getUss"
        binding.imageView.setOnClickListener {
            var giveUser = sharedPreferences.edit()
            giveUser.putString("uss", getUss)
            giveUser.apply()
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    fun getData(){
        movieAdapter = MovieAdapter(emptyList()){movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", movie.id)
            startActivity(intent)
        }
        val getVM = ViewModelProvider(this).get(MovieViewModel::class.java)
        getVM.getMovie()
        binding.rcvcon.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvcon.adapter = movieAdapter
        getVM.liveDataMovie.observe(this,{ movie ->
            movieAdapter.listMovie = movie
            movieAdapter.notifyDataSetChanged()
        })

    }
}