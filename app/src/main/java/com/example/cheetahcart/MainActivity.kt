package com.example.cheetahcart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cheetahcart.cart.CartFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, CartFragment(), CartFragment::class.java.simpleName).commit()
    }
}
