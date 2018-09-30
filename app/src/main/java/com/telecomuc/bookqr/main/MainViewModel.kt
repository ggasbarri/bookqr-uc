package com.telecomuc.bookqr.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.data.BookRepository


class MainViewModel(private val repo: BookRepository) : ViewModel() {

    val lastSeenBooks: LiveData<PagedList<BookData>> =
            LivePagedListBuilder<Int, BookData>(repo.getLastSeenBooks(), 10).build()


}