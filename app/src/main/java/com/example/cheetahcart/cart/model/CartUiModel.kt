package com.example.cheetahcart.cart.model

data class CartUiModel(
    val name: String,
    val unitPrice: String,
    val unitType: String,
    val isSubstitutable: Boolean,
    val subTotal: String,
    val quantity: String,
    val imageUrl: String
)
