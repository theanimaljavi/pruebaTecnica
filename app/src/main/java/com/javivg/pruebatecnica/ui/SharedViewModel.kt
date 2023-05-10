package com.javivg.pruebatecnica.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javivg.pruebatecnica.api.Repositorio
import com.javivg.pruebatecnica.api.model.ListaPersonajes
import kotlinx.coroutines.launch
import retrofit2.Response

//se utiliza para separar la lógica de negocio de la UI y
// para proporcionar una capa intermedia entre el repositorio y los componentes de la aplicación
class SharedViewModel(val repositorio: Repositorio): ViewModel() {
    var listaPersonajes = MutableLiveData<Response<ListaPersonajes>>()

    fun getPersonajes(page:Int){
        viewModelScope.launch {
            val personajes = repositorio.getPersonajes(page)
            listaPersonajes.value = personajes
        }
    }
}