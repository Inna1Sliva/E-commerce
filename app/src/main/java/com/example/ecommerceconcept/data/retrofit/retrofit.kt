package com.example.ecommerceconcept.data.retrofit

import com.example.ecommerceconcept.domain.model.cart.Cart
import com.example.ecommerceconcept.domain.model.category.ProductDeteils
import com.example.ecommerceconcept.domain.model.category.SelectCategory
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET



interface RetrofitClient {

    @GET(Constants.CATEGORI_URL)
    fun getData(): Call<SelectCategory>?

    @GET(Constants.CART)
    fun getCart():Call<Cart>

    @GET(Constants.DETEILE)
    fun getDataDeteile():Call<ProductDeteils>
}
