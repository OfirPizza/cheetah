package com.example.cheetahcart.data

import com.example.cheetahcart.network.NetworkManager
import com.example.cheetahcart.network.model.CartResponse
import io.reactivex.Single
import java.text.NumberFormat
import java.util.*


class DataManager {
    private val TAG = DataManager::class.java.simpleName


    companion object {
        val INSTANCE = DataManager()
    }


    fun getCart(): Single<CartResponse> {
        return NetworkManager.INSTANCE.getCart()
    }

   fun centsToDollar(cents: Long): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return numberFormat.format(cents / 100.0)
    }

}