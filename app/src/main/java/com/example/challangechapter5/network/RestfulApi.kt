package com.example.challangechapter5.network

import com.example.challangechapter5.model.movieApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestfulApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<movieApi<com.example.challangechapter5.model.Result>>
}