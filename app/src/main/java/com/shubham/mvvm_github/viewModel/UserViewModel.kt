package com.shubham.mvvm_github.viewModel

import androidx.lifecycle.ViewModel
import com.shubham.mvvm_github.model.User
import com.shubham.mvvm_github.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    private val stateFlow = MutableStateFlow(User())
    val userFlow = stateFlow.asStateFlow()

    suspend fun fetchUser(userName: String) {
        try {
            stateFlow.value = userRepository.getUser(userName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}