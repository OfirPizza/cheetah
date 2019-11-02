package com.example.cheetahcart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cheetahcart.cart.CartFragment
import com.example.cheetahcart.dialog.errorDialog.NetworkErrorDialogFragment
import com.example.cheetahcart.network.NetworkManager

class MainActivity : AppCompatActivity() {

    private var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initNetworkManager()
        initViews()
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        setObservers()
    }

    private fun setObservers() {
        mainActivityViewModel?.getNetworkStatusLiveData()
            ?.observe(this, Observer { onNetworkStateChange(it) })
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, CartFragment(), CartFragment::class.java.simpleName).commit()
    }

    private fun initNetworkManager() {
        NetworkManager.INSTANCE.enable(this)
    }


    private fun onNetworkStateChange(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            supportFragmentManager
                .findFragmentByTag(NetworkErrorDialogFragment::class.java.simpleName)
                ?.let {
                    (it as DialogFragment).dismiss()
                }
            return
        }

        showNetworkError()
    }

    private fun showNetworkError() {
        NetworkErrorDialogFragment().show(
            supportFragmentManager,
            NetworkErrorDialogFragment()::class.java.simpleName
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkManager.INSTANCE.disable(this)
    }

}
