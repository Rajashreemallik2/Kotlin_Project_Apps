package com.example.movies.activites.api

import com.example.movies.activites.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("movies")
    fun getMovies(): Call<List<Movie>>
}