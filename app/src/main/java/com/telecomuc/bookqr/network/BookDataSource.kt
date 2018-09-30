package com.telecomuc.bookqr.network

import androidx.lifecycle.MutableLiveData
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.utils.Resource
import com.telecomuc.bookqr.utils.awaitResult
import com.telecomuc.bookqr.utils.getOrThrow
import kotlinx.coroutines.experimental.*

class BookDataSource(private val retrofitClient: RetrofitClient) {

    fun getBookForId(id: String): MutableLiveData<Resource<BookData?>> {

        val result = MutableLiveData<Resource<BookData?>>()
        result.postValue(Resource.loading(null))

        GlobalScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT, null, {
            try {
                val response = retrofitClient.apiService
                        .getBookForId(id).awaitResult().getOrThrow()
                result.postValue(Resource.success(response.response))
            } catch (e: Exception) {
                result.postValue(Resource.error("Error while fetching", null))
            }
        })

        return result

    }


}