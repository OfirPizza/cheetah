package com.example.cheetahcart.dialog.errorDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.cheetahcart.R

class NetworkErrorDialogFragment : DialogFragment() {

    private lateinit var networkErrorDialogViewModel: NetworkErrorDialogViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.fragment_network_error_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViewModel() {
        networkErrorDialogViewModel =
            ViewModelProviders.of(this).get(NetworkErrorDialogViewModel::class.java)
    }


    private fun initViews() {
        setErrorText()
        setBtn()
    }

    private fun setBtn() {
    }

    private fun setErrorText() {
    }
}