package com.example.cheetahcart.data

import com.example.cheetahcart.network.NetworkManager
import com.example.cheetahcart.network.model.CartResponse
import io.reactivex.Single


class DataManager {
    private val TAG = DataManager::class.java.simpleName


    companion object {
        val INSTANCE = DataManager()
    }


    fun getCart(): Single<CartResponse> {
        return NetworkManager.INSTANCE.getCart()
    }

}