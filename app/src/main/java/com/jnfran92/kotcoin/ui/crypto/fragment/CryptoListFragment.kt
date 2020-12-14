package com.jnfran92.kotcoin.ui.crypto.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.jnfran92.kotcoin.databinding.FragmentCryptoListBinding
import com.jnfran92.kotcoin.presentation.crypto.CryptoListViewModel
import com.jnfran92.kotcoin.ui.crypto.activity.CryptoActivity
import com.jnfran92.kotcoin.ui.crypto.adapter.CryptoListAdapter
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for displaying Crypto List
 */
class CryptoListFragment : Fragment() {

    @Inject
    lateinit var cryptoListAdapter: CryptoListAdapter
    @Inject
    lateinit var cryptoLayoutManager: RecyclerView.LayoutManager

    /**
     * view binding
     */
    lateinit var binding: FragmentCryptoListBinding

    /**
     * view model
     */
    val viewModel: CryptoListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView: ")
        this.binding = FragmentCryptoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initInjection()
        this.initViewElements()
        this.initViewModel()
    }

    private fun initInjection(){
        val cryptoComponent = (activity as CryptoActivity).getCryptoComponent()
        cryptoComponent.inject(this)
    }

    private fun initViewElements(){
        this.binding.rvCryptoFragmentCryptoList.adapter = this.cryptoListAdapter
        this.binding.rvCryptoFragmentCryptoList.layoutManager = this.cryptoLayoutManager
    }

    private fun initViewModel() {
        Timber.d("initViewModel")
        this.viewModel.cryptoList.observe(viewLifecycleOwner) {
            this.cryptoListAdapter.setData(it)
        }
    }
}