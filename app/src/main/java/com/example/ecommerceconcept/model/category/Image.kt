package com.example.ecommerceconcept.model.category

import com.google.gson.annotations.SerializedName

data class Image (
        @SerializedName("image")
        val image: String,
        @SerializedName("imagetwe")
        val imagetwe: String
        )