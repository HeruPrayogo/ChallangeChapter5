package com.example.challangechapter5.viewmodel

import com.example.challangechapter5.model.Result
import android.graphics.Movie
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide.init
import com.example.challangechapter5.MovieAdapter
import com.example.challangechapter5.databinding.ActivityHomeBinding
import com.example.challangechapter5.model.movieApi
import com.example.challangechapter5.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel(){
     lateinit var liveDataMovie:MutableLiveData<List<Result>>

    init {
        liveDataMovie = MutableLiveData()
    }
    fun getMovie() {
        RetrofitClient.instance.getPopularMovies(
            apiKey = "e73ba4baa44323fa06e5497760f26ab5",
            page = 1
        ).enqueue(object : Callback<movieApi<com.example.challangechapter5.model.Result>> {
            override fun onResponse(call: Call<movieApi<Result>>, response: Response<movieApi<Result>>) {
                if (response.isSuccessful){
                    val movieresponse = response.body()
                    liveDataMovie.postValue(movieresponse?.results)

                }else{
                    liveDataMovie.value = emptyList()
                }
            }

            override fun onFailure(call: Call<movieApi<Result>>, t: Throwable) {
                liveDataMovie.value = emptyList()
            }

        })


    }
}