package com.example.challangechapter5.viewmodel

import com.example.challangechapter5.model.Result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.challangechapter5.model.movieApi
import com.example.challangechapter5.network.RetrofitClient

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel(){
     lateinit var liveDataMovie:MutableLiveData<List<Result>>
     lateinit var liveDetail: MutableLiveData<com.example.challangechapter5.model.Result>
    init {
        liveDataMovie = MutableLiveData()
        liveDetail = MutableLiveData()
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
    fun getMovieDetail(movieId:Int) {
            RetrofitClient.instance.getMovieDetails(movieId, "e73ba4baa44323fa06e5497760f26ab5")
                .enqueue(object : Callback<com.example.challangechapter5.model.Result> {
                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        if (response.isSuccessful) {
                            val movie = response.body()
                            liveDetail.value = movie
                        }
                    }

                    override fun onFailure(call: Call<Result>, t: Throwable) {
                        liveDataMovie.value = emptyList()
                    }

                })
    }



}