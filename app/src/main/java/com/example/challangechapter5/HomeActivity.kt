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
        binding.imageView.setOnClickListener {

        }
        sharedPreferences = getSharedPreferences("InsertAcc", MODE_PRIVATE)
        var getUss = sharedPreferences.getString("uss", "")
        binding.welcome.text ="Welcom, $getUss"
    }

    fun getData(){

        val getVM = ViewModelProvider(this).get(MovieViewModel::class.java)
        getVM.getMovie()
        getVM.liveDataMovie.observe(this,{
            movieAdapter.onItemClick =  { movie ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("movie", movie)
                startActivity(intent)
                binding.rcvcon.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rcvcon.adapter = MovieAdapter(it)
            }


        })

    }
}