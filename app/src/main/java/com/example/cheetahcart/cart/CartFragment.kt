package com.example.cheetahcart.cart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cheetahcart.R
import com.example.cheetahcart.cart.adapter.CartAdapter
import com.example.cheetahcart.cart.model.CartUiModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {


    private var cartViewModel: CartViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initObservers()
        setRecyclerView()

    }

    private fun setRecyclerView() {
        cart_recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

    }


    private fun initViewModel() {
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
    }


    private fun initObservers() {
        cartViewModel?.cartTotalValueLiveData?.observe(this, Observer { setTotalCartValue(it) })
        cartViewModel?.productListLiveData?.observe(this, Observer { onProductsReceived(it) })
    }

    private fun onProductsReceived(products: List<CartUiModel>) {
        cart_recyclerView.adapter = CartAdapter(products)
    }

    private fun setTotalCartValue(amount: String) {

    }


}