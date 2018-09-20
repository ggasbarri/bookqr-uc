package com.telecomuc.bookqr.network

import android.arch.lifecycle.MutableLiveData
import com.telecomuc.bookqr.FetchingState
import com.telecomuc.bookqr.data.BookData

class BookDataSource(private val retrofitClient: RetrofitClient) {

    val fetchingState = MutableLiveData<FetchingState>()


    suspend fun getBookForId(id: String): BookData? {

        fetchingState.postValue(FetchingState.Fetching)

        val response = retrofitClient.apiService.getBookForId(id).await()

        if (response.isSuccessful) {

            val bookResponse = response.body()

            bookResponse?.let {
                val data = bookResponse.data

                if (data == null) {
                    fetchingState.postValue(FetchingState.Failure)
                } else {
                    fetchingState.postValue(FetchingState.Idle)
                    return data
                }

            } ?: fetchingState.postValue(FetchingState.Failure)

        } else fetchingState.postValue(FetchingState.Failure)

        return null

    }


}