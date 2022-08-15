package com.example.homework15.retrofitclient

import com.example.homework15.retrofitclient.services.LoginRequestService
import com.example.homework15.retrofitclient.services.RegisterRequestService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://reqres.in/api/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory
                    .create(moshi)
            )
            .build()
    }

    val login by lazy{
        retrofitBuilder.create(LoginRequestService::class.java)
    }
    val register by lazy {
        retrofitBuilder.create(RegisterRequestService::class.java)
    }

}