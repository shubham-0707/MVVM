package com.shubham.mvvm_github.repository

import com.shubham.mvvm_github.apiService.ApiService
import com.shubham.mvvm_github.model.User

class UserRepository(private val apiService: ApiService) {

    suspend fun getUser(userName: String): User {
        return apiService.getUser(userName)
    }

}