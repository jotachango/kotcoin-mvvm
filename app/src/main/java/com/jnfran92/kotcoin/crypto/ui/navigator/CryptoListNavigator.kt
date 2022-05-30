package com.jnfran92.kotcoin.crypto.ui.navigator

import android.view.View
import androidx.navigation.findNavController
import com.jnfran92.kotcoin.crypto.presentation.model.UICrypto
import com.jnfran92.kotcoin.crypto.ui.fragment.CryptoListFragmentDirections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoListNavigator @Inject constructor() {

    fun goToCryptoDetails(view: View, item: UICrypto) {
        val direction =
            CryptoListFragmentDirections.actionCryptoListFragmentToCryptoDetailsFragment(item)
        view.findNavController().navigate(direction)
    }
}