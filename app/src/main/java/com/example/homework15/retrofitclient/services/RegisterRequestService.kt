package com.example.homework15.retrofitclient.services

import com.example.homework15.models.LoginRequestModel
import com.example.homework15.models.RegisterModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterRequestService {
    @POST("register")
    suspend fun registerUser(@Body body: LoginRequestModel): Response<RegisterModel>
}