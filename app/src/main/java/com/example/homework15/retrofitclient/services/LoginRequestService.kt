package com.example.homework15.retrofitclient.services

import com.example.homework15.models.LoginRequestModel
import com.example.homework15.models.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRequestService {
    @POST("login")
    suspend fun login(@Body body: LoginRequestModel): Response<LoginResponseModel>
}