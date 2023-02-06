package com.example.ecommerceconcept.model.cart

import java.time.temporal.TemporalAmount

data class Basket(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String,
    val amount: Int = 1
)