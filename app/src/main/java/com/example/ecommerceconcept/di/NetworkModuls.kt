package com.example.ecommerceconcept.di


import com.example.ecommerceconcept.retrofit.Constants
import com.example.ecommerceconcept.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModuls {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesAPIService(retrofit: Retrofit):RetrofitClient{
        return retrofit.create(RetrofitClient::class.java)
    }
}