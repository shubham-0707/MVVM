package com.shubham.mvvm_github.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String = "Invalid",
    @SerializedName("login")
    val login: String = "Invalid",
    @SerializedName("location")
    val location: String = "Earth",
    @SerializedName("followers")
    val followers: Int = -1,
    @SerializedName("following")
    val following: Int = -1,
)