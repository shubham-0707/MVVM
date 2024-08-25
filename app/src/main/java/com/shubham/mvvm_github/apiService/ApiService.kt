package com.shubham.mvvm_github.apiService

import com.shubham.mvvm_github.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName: String): User
}