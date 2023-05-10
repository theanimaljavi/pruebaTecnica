package com.javivg.pruebatecnica.api

import com.javivg.pruebatecnica.api.model.ListaPersonajes
import retrofit2.Response

/*
 proporciona una capa de abstracción entre la fuente de datos
  y las capas superiores de la aplicación que necesitan acceder a los datos
 */
class Repositorio {
    suspend fun getPersonajes(page:Int): Response<ListaPersonajes> {
        return RetrofitInstance.api.getPersonajes(page)
    }
}

