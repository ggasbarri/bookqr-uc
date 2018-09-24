package com.telecomuc.bookqr.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {

    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(EndpointInterface::class.java)

}

const val BASE_URL = "http://192.168.99.100:8055/"