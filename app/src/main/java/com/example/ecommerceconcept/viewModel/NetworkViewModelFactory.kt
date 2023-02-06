package com.example.ecommerceconcept.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceconcept.repository.NetworkRepository
import javax.inject.Inject

class NetworkViewModelFactory @Inject constructor(private val repository: NetworkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}