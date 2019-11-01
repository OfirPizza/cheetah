package com.example.cheetahcart.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cheetahcart.cart.model.CartUiModel
import com.example.cheetahcart.cart.model.ProductUnitType
import com.example.cheetahcart.data.DataManager
import com.example.cheetahcart.network.model.CartResponse
import com.example.cheetahcart.network.model.Product
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.util.*


class CartViewModel : ViewModel() {

    private val TAG = CartViewModel::class.java.simpleName
    private var disposable: Disposable? = null
    val productListLiveData = MutableLiveData<List<CartUiModel>>()
    val cartTotalValueLiveData = MutableLiveData<String>()


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
        postTotalCartValue(cartResponse.cartTotal)
        postProductList(cartResponse.productList)

    }

    private fun postProductList(productList: ArrayList<Product>) {
        productListLiveData.postValue(productList.map { toCartUiModel(it) })
    }

    private fun postTotalCartValue(cartTotal: Long) {
        cartTotalValueLiveData.postValue(centsToDollar(cartTotal))
    }

    private fun toCartUiModel(product: Product): CartUiModel {
        val name = product.productItemInfo.name
        val unitType = ProductUnitType.getUnitTypeByName(product.packageType)
        val unitPrice = centsToDollar(unitType.getPrice(product))
        val subTotal = centsToDollar(product.subTotal)
        val unitImageUrl = unitType.getImageUrl(product)

        return CartUiModel(
            name = name,
            unitPrice = unitPrice,
            unitType = unitType.name.toLowerCase(Locale.US),
            isSubstitutable = product.substitutable,
            subTotal = subTotal,
            quantity = product.quantity.toString(),
            imageUrl = unitImageUrl
        )

    }


    private fun centsToDollar(cents: Long): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return numberFormat.format(cents / 100.0)
    }

    private fun postGetCartFailed(ex: Throwable) {
        Log.e(TAG, ex.message.toString())
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}