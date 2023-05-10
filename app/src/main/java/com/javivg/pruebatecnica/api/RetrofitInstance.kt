package com.javivg.pruebatecnica.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// objeto singleton para recoger los datos de la API
// se usa Gson para convertir los objetos JSON devueltos
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // api es una instancia de SimpleApi que puede utilizarse para realizar solicitudes HTTP
    val api:SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}
