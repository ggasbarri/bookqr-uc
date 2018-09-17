package com.telecomuc.bookqr.koin

import com.telecomuc.bookqr.main.LastSeenAdapter
import com.telecomuc.bookqr.main.MainDataSource
import com.telecomuc.bookqr.main.MainRepository
import com.telecomuc.bookqr.main.MainViewModel
import com.telecomuc.bookqr.network.RetrofitClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    // Single Retrofit Client
    single { RetrofitClient() }


    // single instance of MainDataSource
    single { MainDataSource() }

    // single instance of MainRepository
    single { MainRepository(get()) }

    // MainViewModel ViewModel
    viewModel(name = "MainViewModel") { MainViewModel(get()) }
}


val mainModule = module {

    factory { LastSeenAdapter() }

}