package com.example.ecommerceconcept

import android.app.Application
import com.example.ecommerceconcept.di.ApplicationComponent
import com.example.ecommerceconcept.di.DaggerApplicationComponent



class DaggerApplication: Application() {

    lateinit var appComponetns:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponetns = DaggerApplicationComponent
            .builder()
            .build()

    }

}