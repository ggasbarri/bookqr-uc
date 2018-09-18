package com.telecomuc.bookqr.main

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.data.MainDao
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainRepository(private val mainDao: MainDao,
                     private val dataSource: MainDataSource) {

    // TODO: Check if it works after inserting
    fun getBookForId(id: String): LiveData<BookData> {

        launch {

            // TODO: Check if background thread
            val deferredResponse = async {
                val response = dataSource.getBookForId(id)
                val data = response.data
                if (data == null) {
                    //TODO: Failed
                } else {
                    mainDao.insertBook(data)
                }
            }
        }

        return mainDao.getBookById(id)

    }

    fun getLastSeenBooks(): DataSource.Factory<Int, BookData> {
        return mainDao.getLastSeenBooks()
    }

}