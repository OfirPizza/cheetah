package com.example.cheetahcart.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cheetahcart.data.DataManager
import com.example.cheetahcart.network.model.CartResponse
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CartViewModel : ViewModel() {

    private var disposable: Disposable? = null
    private val TAG = CartViewModel::class.java.simpleName


    init {
        getCart()
    }


    private fun getCart() {
        disposable = DataManager.INSTANCE.getCart()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onError = { postGetCartFailed(it) },
                onSuccess = { postGetCart(it) }
            )

    }

    private fun postGetCart(cartResponse: CartResponse) {
        val x = 5
    }

    private fun postGetCartFailed(ex: Throwable) {
        Log.e(TAG, ex.message.toString())
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}