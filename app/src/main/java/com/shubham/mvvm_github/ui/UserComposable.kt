package com.shubham.mvvm_github.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shubham.mvvm_github.fragments.UserListeners
import com.shubham.mvvm_github.model.User

@Composable
fun UserComposable(user: User, userListeners: UserListeners) {
    val userValue = remember{ mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserDetails(user = user)

        Spacer(modifier = Modifier.height(24.dp))

        BasicTextField(
            value = userValue.value,
            onValueChange = { userValue.value = it },
            modifier = Modifier.background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(48.dp)
                .background(Color.Yellow, RoundedCornerShape(100.dp))
                .clickable {
                    if (userValue.value.isBlank() || userValue.value.isEmpty()) {
                        Toast
                            .makeText(
                                context,
                                "Please enter some name in text field",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    } else {
                        userListeners.getUser(userValue.value)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Get User Details")
        }
    }
}

@Composable
fun UserDetails(user: User) {
    Text(text = "name is: ${user.name}", color = Color.Black)
    Text(text = "login is: ${user.login}", color = Color.Black)
    Text(text = "location is: ${user.location}", color = Color.Black)
    Text(text = "followers count is: ${user.followers}", color = Color.Black)
    Text(text = "following count is: ${user.following}", color = Color.Black)
}