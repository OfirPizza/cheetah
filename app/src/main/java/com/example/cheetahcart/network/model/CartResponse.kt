package com.example.cheetahcart.network.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class CartResponse(

    @SerializedName("cart_total")
    val cartTotal: Long,

    @SerializedName("order_items_information")
    val productList: ArrayList<Product>
)


data class Product(

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("sub_total")
    val subTotal: Long,

    @SerializedName("packaging_type")
    val packageType: String,

    @SerializedName("substitutable")
    val substitutable: Boolean,

    @SerializedName("product")
    val productItemInfo: ProductItemInfo
)


data class ProductItemInfo(

    @SerializedName("name")
    val name: String,

    @SerializedName("unit_photo_filename")
    val unitImageUrl: String,

    @SerializedName("pack_photo_file")
    val packImageUrl: String,

    @SerializedName("unit_price")
    val unitPrice: Int,

    @SerializedName("case_price")
    val casePrice: Int,

    @SerializedName("weight_price")
    val weightPrice: Int
)