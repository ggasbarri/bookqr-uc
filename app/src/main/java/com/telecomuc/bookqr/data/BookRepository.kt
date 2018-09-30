package com.telecomuc.bookqr.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.telecomuc.bookqr.network.BookDataSource
import com.telecomuc.bookqr.utils.FetchingState
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import java.util.*

class BookRepository(private val mainDao: BookDao,
                     private val dataSource: BookDataSource) {

    private val fetchingState = MutableLiveData<FetchingState>()

    fun getBookForId(id: String): LiveData<BookData> {

        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null, {

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

                                    mainDao.insertBook(it)
                                }

                            }
                            FetchingState.ERROR -> {

                            }
                        }

                    }
        })

        return mainDao.getBookById(id)

    }

    fun getLastSeenBooks(): DataSource.Factory<Int, BookData> {
        return mainDao.getLastSeenBooks()
    }

    fun getFetchingState(): LiveData<FetchingState> {
        return fetchingState
    }

}