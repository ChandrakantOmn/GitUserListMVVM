package com.foo.assignment.data.remote

import com.foo.assignment.data.model.BooksResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("books/v1/volumes")
    fun getBooks(@Query("q") q: String, @Query("maxResults") maxResults: Int)
            : Single<BooksResponse>
}