package com.telecomuc.bookqr.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.telecomuc.bookqr.network.BookDataSource
import com.telecomuc.bookqr.utils.FetchingState
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import java.util.*

class BookRepository(private val mainDao: BookDao,
                     private val dataSource: BookDataSource) {

    private val fetchingState = MutableLiveData<FetchingState>()

    fun getBookForId(id: String): LiveData<BookData> {

        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, null, {

            dataSource.getBookForId(id)
                    .observeForever {

                        if (it == null) return@observeForever

                        fetchingState.postValue(it.status)

                        when (it.status) {

                            FetchingState.FETCHING -> {

                            }
                            FetchingState.SUCCESS -> {

                                it.data?.let {

                                    // Add current date to request date
                                    val requestDate = Calendar
                                            .getInstance()
                                            .time
                                            .toString()

                                    it.requestDate = requestDate

                                    GlobalScope.launch(Dispatchers.IO) {
                                        mainDao.insertBook(it)
                                    }
                                }

                            }
                            FetchingState.ERROR -> {

                            }
                        }

                    }
        })
        with(Dispatchers.IO) {
            return mainDao.getBookById(id)
        }

    }

    fun getLastSeenBooks(): DataSource.Factory<Int, BookData> {
        return mainDao.getLastSeenBooks()
    }

    fun getFetchingState(): LiveData<FetchingState> {
        return fetchingState
    }

}