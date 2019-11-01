package com.example.cheetahcart.network

import com.example.cheetahcart.network.model.CartResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkApi {


    companion object {
        const val BASE_URL = "http://www.mocky.io/"
        const val CONTENT_TYPE = "Content-Type:application/json"
    }

    @Headers(CONTENT_TYPE)
    @GET("v2/59c791ed1100005300c39b93")
    fun getCart(): Single<CartResponse>


}