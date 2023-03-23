package com.example.ecommerceconcept.domain.model.category


import com.google.gson.annotations.SerializedName

data class SelectCategory(
    @SerializedName("best_seller")
    val bestSeller: List<BestSeller>,
    @SerializedName("home_store")
    val homeStore: List<HomeStore>
)