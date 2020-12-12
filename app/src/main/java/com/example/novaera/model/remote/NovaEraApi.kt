package com.example.novaera.model.remote

import com.example.novaera.model.remote.pojo.Details
import com.example.novaera.model.remote.pojo.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NovaEraApi {

    @GET("products/")
    suspend fun fetchProducts() : Response<List<Products>>

    @GET("details/ {id}")
    suspend fun fetchDetails(@Path("id") id:Int) :  Response<List<Details>>
}