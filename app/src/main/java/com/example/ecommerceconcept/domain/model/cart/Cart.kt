package com.example.ecommerceconcept.domain.model.cart

data class Cart(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)