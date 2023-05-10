package com.javivg.pruebatecnica.api

import com.javivg.pruebatecnica.api.model.ListaPersonajes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/* Se define un método getPersonajes()
que realiza una solicitud HTTP GET a la URL especificada en la anotación @GET.
El parámetro page se utiliza para especificar el número de página de resultados que se desean recuperar
 */
interface SimpleApi {

    @GET("api/character")
    suspend fun getPersonajes(@Query("page") page: Int): Response<ListaPersonajes>

}