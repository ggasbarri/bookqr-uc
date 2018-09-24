package com.telecomuc.bookqr.network

import com.telecomuc.bookqr.data.BookResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointInterface {

    @GET("books/{id}")
    fun getBookForId(@Path("id") id: String): Deferred<Response<BookResponse>>

}