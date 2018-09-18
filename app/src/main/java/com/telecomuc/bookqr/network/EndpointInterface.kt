package com.telecomuc.bookqr.network

import com.telecomuc.bookqr.data.BookResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointInterface {

    @GET("book/{id}")
    fun getBookForId(@Path("id") id: String): Deferred<BookResponse>

}