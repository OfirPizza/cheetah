package com.example.cheetahcart.network

import android.content.Context
import android.net.*
import androidx.lifecycle.MutableLiveData
import com.example.cheetahcart.network.model.CartResponse
import io.reactivex.Single

class NetworkManager : ConnectivityManager.NetworkCallback() {

    private  var connectivityManager: ConnectivityManager? = null
    val isNetworkAvailable = MutableLiveData<Boolean>()

    companion object {
        val INSTANCE = NetworkManager()
    }


    fun getCart(): Single<CartResponse> {
        return RetrofitManager.INSTANCE.getNetworkApi().getCart()
    }


    fun enable(context: Context) {
        val networkRequest =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.registerNetworkCallback(networkRequest, this)
        checkCurrentConnection(connectivityManager?.activeNetworkInfo)
    }

    fun disable(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(this)
    }

    private fun checkCurrentConnection(networks: NetworkInfo?) {
        val isConnected = networks != null && networks.isConnected

        postNetworkStatus(isConnected)
    }


    override fun onAvailable(network: Network) {
        postNetworkStatus(true)
    }


    override fun onLost(network: Network) {
     checkCurrentConnection(connectivityManager?.activeNetworkInfo)
    }

    private fun postNetworkStatus(haveInternet: Boolean) {
        isNetworkAvailable.postValue(haveInternet)

    }
}