package com.telecomuc.bookqr.data

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM book_data ORDER BY datetime(requestDate)")
    fun getLastSeenBooks(): DataSource.Factory<Int, BookData>

    @Query("SELECT * FROM book_data WHERE id = :id")
    fun getBookById(id: String): LiveData<BookData>

    @Insert(onConflict = REPLACE)
    fun insertBook(bookData: BookData)

}