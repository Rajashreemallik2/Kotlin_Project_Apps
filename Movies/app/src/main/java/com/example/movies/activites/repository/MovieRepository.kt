package com.example.movies.activites.repository

import com.example.movies.activites.api.MovieApi
import com.example.movies.activites.model.Movie
import retrofit2.Call

class MovieRepository(private val apiService: MovieApi) {
    fun getMovies(): Call<List<Movie>>{
        return apiService.getMovies()
    }
}