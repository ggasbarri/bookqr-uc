package com.telecomuc.bookqr.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM book_data ORDER BY datetime(requestDate)")
    fun getLastSeenBooks(): DataSource.Factory<Int, BookData>

    @Query("SELECT * FROM book_data WHERE id = :id")
    fun getBookById(id: String): LiveData<BookData>

    @Insert(onConflict = REPLACE)
    fun insertBook(bookData: BookData)

}