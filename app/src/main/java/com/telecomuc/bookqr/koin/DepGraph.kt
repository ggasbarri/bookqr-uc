package com.telecomuc.bookqr.koin

import androidx.room.Room
import com.telecomuc.bookqr.data.BookRepository
import com.telecomuc.bookqr.data.Database
import com.telecomuc.bookqr.detail.DetailViewModel
import com.telecomuc.bookqr.main.MainViewModel
import com.telecomuc.bookqr.network.BookDataSource
import com.telecomuc.bookqr.network.RetrofitClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    // Single Retrofit Client
    single { RetrofitClient() }

    // single instance of AppDatabase
    single {
        Room.databaseBuilder(androidApplication(), Database::class.java, "bookqr-db")
                .build()
    }

    single {
        get<Database>().bookDao()
    }

    single { BookDataSource(get()) }

    single { BookRepository(get(), get()) }

    // MainActivity

    viewModel(name = mainVmName) { MainViewModel(get()) }


    // DetailActivity

    viewModel(name = detailVmName) { DetailViewModel(get()) }
}

const val detailVmName = "DetailViewModel"

const val mainVmName = "MainViewModel"