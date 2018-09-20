package com.telecomuc.bookqr.data

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.telecomuc.bookqr.FetchingState
import com.telecomuc.bookqr.network.BookDataSource
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*

class BookRepository(private val mainDao: BookDao,
                     private val dataSource: BookDataSource) {


    // TODO: Check if it works after inserting
    fun getBookForId(id: String): LiveData<BookData> {

        launch {
            // TODO: Check if background thread
            val deferredResponse = async {

                dataSource.getBookForId(id)

            }

            val bookData = deferredResponse.await()

            bookData?.let {
                // Add current date to request date
                val requestDate = Calendar
                        .getInstance()
                        .time
                        .toString()

                bookData.requestDate = requestDate

                mainDao.insertBook(bookData)
            }
        }

        return mainDao.getBookById(id)

    }

    fun getLastSeenBooks(): DataSource.Factory<Int, BookData> {
        return mainDao.getLastSeenBooks()
    }

    fun getFetchingState(): LiveData<FetchingState> {
        return dataSource.fetchingState
    }

}