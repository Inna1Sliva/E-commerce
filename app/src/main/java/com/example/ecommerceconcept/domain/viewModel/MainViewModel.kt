package com.example.ecommerceconcept.domain.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceconcept.domain.repository.NetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val repository: NetworkRepository): ViewModel() {

    val AllHomeStoreList = repository.getHomeStoreObserver()
    val AllBestSallerList = repository.getBestSellerObserver()
    val AllCartList = repository.getCartListObserver()
    val AllCart = repository.getCartObserver()
    val AllDetails = repository.getDetailObserver()
    val AllImage = repository.getImagOserver()


    init {
        viewModelScope.launch {
            repository.getCategory()
        }
        viewModelScope.launch {
            repository.getBestSellerCall()
        }
        viewModelScope.launch {
            repository.getCartList()
        }
        viewModelScope.launch {
            repository.getDetailList()
        }

    }

}