package com.example.cheetahcart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cheetahcart.network.NetworkManager

class MainActivityViewModel : ViewModel() {

    fun getNetworkStatusLiveData(): MutableLiveData<Boolean> {
        return NetworkManager.INSTANCE.isNetworkAvailable
    }

}
