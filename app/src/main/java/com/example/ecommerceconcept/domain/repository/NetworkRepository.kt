package com.example.ecommerceconcept.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.ecommerceconcept.domain.model.cart.Basket
import com.example.ecommerceconcept.domain.model.cart.Cart
import com.example.ecommerceconcept.domain.model.category.BestSeller
import com.example.ecommerceconcept.domain.model.category.HomeStore
import com.example.ecommerceconcept.domain.model.category.ProductDeteils
import com.example.ecommerceconcept.domain.model.category.SelectCategory
import com.example.ecommerceconcept.data.retrofit.RetrofitClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject



class NetworkRepository @Inject constructor(private val retrofitClient: RetrofitClient) {

    private var livedataList = MutableLiveData<List<HomeStore>?>()
    private var bestSellerList = MutableLiveData<List<BestSeller>?>()
    private var cartList = MutableLiveData<List<Basket>?>()
    private var cart = MutableLiveData<Cart?>()
    private var detailist = MutableLiveData<ProductDeteils?>()
    private var imageList = MutableLiveData<List<String>?>()


    init {
        livedataList=MutableLiveData()
        bestSellerList = MutableLiveData()
        cartList = MutableLiveData()
        cart = MutableLiveData()
        detailist = MutableLiveData()
        imageList= MutableLiveData()
    }

    fun getHomeStoreObserver():MutableLiveData<List<HomeStore>?>{
        return  livedataList
    }

    fun getBestSellerObserver():MutableLiveData<List<BestSeller>?>{
        return bestSellerList
    }
    fun getCartListObserver():MutableLiveData<List<Basket>?>{
        return cartList
    }
    fun getCartObserver(): MutableLiveData<Cart?>{
        return cart
    }
    fun getDetailObserver(): MutableLiveData<ProductDeteils?>{
        return detailist
    }
    fun getImagOserver():MutableLiveData<List<String>?>{
        return imageList
    }
    suspend fun getDetailList() = coroutineScope {
        launch {
            val call: Call<ProductDeteils> = retrofitClient.getDataDeteile()
            call?.enqueue(object :Callback<ProductDeteils>{
                override fun onResponse(call: Call<ProductDeteils>, response: Response<ProductDeteils>) {
                    if (response.isSuccessful){
                      detailist.postValue(response.body())
                        imageList.postValue(response.body()?.images)
                    }else{
                        detailist.postValue(null)
                        imageList.postValue(null)


                    }
                }

                override fun onFailure(call: Call<ProductDeteils>, t: Throwable) {
                    detailist.postValue(null)
                    imageList.postValue(null)
                }
            })
        }
    }
    suspend fun getCartList() = coroutineScope {
        launch {
            val call: Call<Cart> = retrofitClient.getCart()
            call.enqueue(object :Callback<Cart>{
                override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                   if (response.isSuccessful){
                       cartList.postValue(response.body()?.basket)
                       cart.postValue(response.body())
                   }else{
                       cartList.postValue(null)
                       cart.postValue(null)
                   }
                }

                override fun onFailure(call: Call<Cart>, t: Throwable) {
                    cartList.postValue(null)
                    cart.postValue(null)
                }

            })
        }
    }

    suspend fun getCategory()= coroutineScope {
        launch{
            val call: Call<SelectCategory>?= retrofitClient.getData()
            call?.enqueue(object : Callback<SelectCategory>{
                override fun onResponse(
                    call: Call<SelectCategory>?,
                    response: Response<SelectCategory>?
                ) {
                    if (response!!.isSuccessful){
                        livedataList.postValue(response.body()?.homeStore)
                    }else{
                        livedataList.postValue(null)
                    }
                }

                override fun onFailure(call: Call<SelectCategory>?, t: Throwable) {
                    livedataList.postValue(null)
                }


            })
        }
    }

    suspend fun getBestSellerCall()= coroutineScope {
        launch {
            val call:Call<SelectCategory>? = retrofitClient.getData()
            call?.enqueue(object :Callback<SelectCategory>{
                override fun onResponse(
                    call: Call<SelectCategory>,
                    response: Response<SelectCategory>
                ) {
                    if (response.isSuccessful){
                        bestSellerList.postValue(response.body()?.bestSeller)
                    }else{
                        bestSellerList.postValue(null)
                    }
                }

                override fun onFailure(call: Call<SelectCategory>, t: Throwable) {
                        bestSellerList.postValue(null)
                }

            })

        }
    }
}





