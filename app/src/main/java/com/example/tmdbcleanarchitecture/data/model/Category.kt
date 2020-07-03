package com.example.tmdbcleanarchitecture.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("id") @Expose
    val id: Int ,
    @SerializedName("name")
    @Expose
    val name: String
)