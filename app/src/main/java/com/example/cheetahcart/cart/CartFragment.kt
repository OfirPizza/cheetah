package com.example.cheetahcart.cart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cheetahcart.R
import com.example.cheetahcart.cart.adapter.CartAdapter
import com.example.cheetahcart.cart.model.CartUiModel
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.view_cart_info.view.*
import kotlinx.android.synthetic.main.view_tool_bar.*
import kotlinx.android.synthetic.main.view_tool_bar.view.*


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
        initViews()

    }

    private fun initViews() {
        setAppBar()
        setRecyclerView()
        setFilterBtn()
    }

    private fun setAppBar() {
        appBar.searchView.setOnSearchClickListener { setAppBarNameVisibility(View.GONE) }
        appBar.searchView.setOnCloseListener {
            setAppBarNameVisibility(View.VISIBLE)
            setToOriginalProductList()
            false
        }

        appBar.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                cartViewModel?.searchProductByName(query)

                return false
            }
        })
    }

    private fun setToOriginalProductList() {
        cartViewModel?.productListLiveData?.value?.let { onProductsReceived(it) }
    }

    private fun setAppBarNameVisibility(visibility: Int) {
        appBar_name.visibility = visibility
    }

    private fun setFilterBtn() {
        cart_info.filter.setOnClickListener {
            onFilterClicked()
        }
    }

    private fun onFilterClicked() {
        val popupMenu = PopupMenu(requireContext(), cart_info.filter)
        popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { onMenuItemClick(it) }
        popupMenu.show()
    }

    private fun onMenuItemClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.filter_lowest -> {
                val products = (cart_recyclerView?.adapter as? CartAdapter)?.getProducts()
                cartViewModel?.filterData(showHighestFirst = false, itemList = products)
            }
            R.id.filter_highest -> {
                val products = (cart_recyclerView?.adapter as? CartAdapter)?.getProducts()
                cartViewModel?.filterData(showHighestFirst = true, itemList = products)
            }
        }
        return true

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
        cartViewModel?.productSearchListLiveData?.observe(this, Observer { onProductsReceived(it) })
        cartViewModel?.productFilteredListLiveData?.observe(this, Observer { onProductsReceived(it) })
        cartViewModel?.getNetworkLiveData()?.observe(this, Observer { onNetworkChange(it)})
    }

    private fun onNetworkChange(connection: Boolean) {
        if (connection) cartViewModel?.getCart()
    }

    private fun onProductsReceived(products: List<CartUiModel>) {
        cart_recyclerView.adapter = CartAdapter(products)
    }

    private fun setTotalCartValue(amount: String) {
        cart_info.total_amount.text = getString(R.string.totalAmount,amount)

    }


}