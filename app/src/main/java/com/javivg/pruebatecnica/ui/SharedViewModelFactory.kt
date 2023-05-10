package com.javivg.pruebatecnica.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javivg.pruebatecnica.api.Repositorio


//El propósito de esta clase es proporcionar al ViewModel
// una instancia de Repositorio al crear un nuevo objeto SharedViewModel.
class SharedViewModelFactory(private val repositorio: Repositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repositorio) as T
    }
}
