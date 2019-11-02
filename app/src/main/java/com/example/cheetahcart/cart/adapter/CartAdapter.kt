package com.example.cheetahcart.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cheetahcart.R
import com.example.cheetahcart.cart.model.CartUiModel
import kotlinx.android.synthetic.main.list_cart_item.view.*

class CartAdapter(private val products: List<CartUiModel>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cart_item, parent, false)


        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun getProducts(): List<CartUiModel> {
        return products
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(products[position])
    }


    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cartUiModel: CartUiModel) {

            loadImage(cartUiModel.imageUrl)
            setSubstitutable(cartUiModel.isSubstitutable)

            itemView.product_name.text = cartUiModel.name
            itemView.product_price.text =
                itemView.resources.getString(R.string.price, cartUiModel.unitPrice)
            itemView.subTotal_price.text = cartUiModel.subTotal
            itemView.quantity.text = cartUiModel.quantity
            itemView.product_unit.text = cartUiModel.unitType
        }


        private fun setSubstitutable(substitutable: Boolean) {
            val color = if (substitutable) itemView.resources.getColor(
                R.color.green,
                null
            ) else itemView.resources.getColor(R.color.white, null)
            itemView.substitutable_img.setBackgroundColor(color)
        }

        private fun loadImage(imageUrl: String) {
            Glide.with(itemView.context).load(imageUrl)
                .apply(RequestOptions().placeholder(R.drawable.ic_image)).into(itemView.product_img)

        }

    }
}