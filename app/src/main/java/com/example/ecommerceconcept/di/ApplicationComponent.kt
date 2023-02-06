package com.example.ecommerceconcept.di

import com.example.ecommerceconcept.CartActivity
import com.example.ecommerceconcept.MainActivity
import com.example.ecommerceconcept.DetailsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModuls::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun cartActyvity(cartActivity: CartActivity)
    fun deteil(productDetails: DetailsActivity)
}