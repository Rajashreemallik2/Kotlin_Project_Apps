package com.example.movies.activites.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.activites.model.Movie
import com.example.movies.activites.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val repository: MovieRepository) : ViewModel()  {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
    get() = _movies

    fun fetchMovies() {
        repository.getMovies().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    _movies.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}