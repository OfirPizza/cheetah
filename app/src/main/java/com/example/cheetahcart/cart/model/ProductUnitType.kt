package com.example.cheetahcart.cart.model

import com.example.cheetahcart.network.model.Product
import java.util.*

enum class ProductUnitType : IProduct {
    UNIT {

        override fun getPrice(product: Product): Long {
            return product.productItemInfo.unitPrice
        }

        override fun getImageUrl(product: Product): String {
            return product.productItemInfo.unitImageUrl
        }
    },
    CASE {

        override fun getPrice(product: Product): Long {
            return product.productItemInfo.casePrice

        }

        override fun getImageUrl(product: Product): String {
            return product.productItemInfo.packImageUrl
        }
    },
    WEIGHT {

        override fun getPrice(product: Product): Long {
            return product.productItemInfo.weightPrice
        }

        override fun getImageUrl(product: Product): String {
            return product.productItemInfo.weightImageUrl
        }
    };

    companion object {
        fun getUnitTypeByName(name: String) = valueOf(name.toUpperCase(Locale.US))
    }
}


interface IProduct {
    fun getPrice(product: Product): Long
    fun getImageUrl(product: Product): String
}