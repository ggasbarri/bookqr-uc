package com.telecomuc.bookqr.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.data.BookRepository


class MainViewModel(private val repo: BookRepository) : ViewModel() {

    val lastSeenBooks: LiveData<PagedList<BookData>> =
            LivePagedListBuilder<Int, BookData>(repo.getLastSeenBooks(), 10).build()


}