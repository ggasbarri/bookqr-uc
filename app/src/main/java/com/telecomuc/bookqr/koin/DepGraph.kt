package com.telecomuc.bookqr.koin

import android.arch.persistence.room.Room
import com.telecomuc.bookqr.data.Database
import com.telecomuc.bookqr.main.MainDataSource
import com.telecomuc.bookqr.main.MainRepository
import com.telecomuc.bookqr.main.MainViewModel
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

    // single instance of MainDao
    single {
        get<Database>().mainDao()
    }

    // single instance of MainDataSource
    single { MainDataSource(get()) }

    // single instance of MainRepository
    single { MainRepository(get(), get()) }

    // MainViewModel ViewModel
    viewModel(name = "MainViewModel") { MainViewModel(get()) }

}