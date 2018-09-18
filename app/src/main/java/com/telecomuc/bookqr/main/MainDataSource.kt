package com.telecomuc.bookqr.main

import com.telecomuc.bookqr.data.BookResponse
import com.telecomuc.bookqr.network.RetrofitClient

class MainDataSource(private val retrofitClient: RetrofitClient) {

    suspend fun getBookForId(id: String): BookResponse {
        return retrofitClient.apiService.getBookForId(id).await()
    }

}