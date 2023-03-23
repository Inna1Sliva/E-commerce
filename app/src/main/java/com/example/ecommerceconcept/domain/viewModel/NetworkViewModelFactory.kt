package com.example.ecommerceconcept.domain.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceconcept.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkViewModelFactory @Inject constructor(private val repository: NetworkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}