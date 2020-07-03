package com.example.tmdbcleanarchitecture.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpokenLanguage (
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null ,

    @SerializedName("name")
    @Expose
    var name: String? = null

)