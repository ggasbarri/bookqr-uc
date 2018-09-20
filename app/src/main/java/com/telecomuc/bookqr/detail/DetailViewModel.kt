package com.telecomuc.bookqr.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.data.BookRepository

class DetailViewModel(val repo: BookRepository) : ViewModel() {

    var bookData: BookData? = null


    fun getBookForID(id: String): LiveData<BookData> {
        return repo.getBookForId(id)
    }

}