package com.shubham.mvvm_github.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.shubham.mvvm_github.apiService.ApiService
import com.shubham.mvvm_github.repository.UserRepository
import com.shubham.mvvm_github.ui.UserComposable
import com.shubham.mvvm_github.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface UserListeners {
    val getUser: (String) -> Unit
}

class UserFragment : Fragment(), UserListeners {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userListeners: UserListeners

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val userRepository = UserRepository(apiService)
        userViewModel = UserViewModel(userRepository)
        userListeners = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val user = userViewModel.userFlow.collectAsState()
            UserComposable(user.value, userListeners)
        }
    }

    companion object {
        const val  TAG  = "USER_FRAGMENT"
    }

    override val getUser: (String) -> Unit = { name ->
        lifecycleScope.launch(Dispatchers.IO) {
            userViewModel.fetchUser(name)
        }
    }

}