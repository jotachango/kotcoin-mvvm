package com.jnfran92.kotcoin.features.crypto.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jnfran92.kotcoin.databinding.FragmentCryptoListBinding
import com.jnfran92.kotcoin.features.crypto.presentation.CryptoListViewModel
import com.jnfran92.kotcoin.features.crypto.presentation.uistate.CryptoListUIState
import com.jnfran92.kotcoin.features.crypto.ui.adapter.CryptoListAdapter
import com.jnfran92.kotcoin.features.crypto.ui.navigator.CryptoListNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for displaying Crypto List
 */
@AndroidEntryPoint
class CryptoListFragment : Fragment() {

    @Inject
    lateinit var cryptoListAdapter: CryptoListAdapter

    @Inject
    lateinit var navigator: CryptoListNavigator

    /**
     * view binding
     */
    private lateinit var binding: FragmentCryptoListBinding

    /**
     * view model
     */
    private val viewModel: CryptoListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView: ")
        this.binding = FragmentCryptoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViewElements()
        this.initViewModel()
    }

    private fun initViewElements() {
        this.cryptoListAdapter.setListener {
            navigator.goToCryptoDetails(requireView(), it)
        }

        val orientation = requireContext().resources.configuration.orientation
        var layoutManager = GridLayoutManager(context, 2)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = GridLayoutManager(context, 3)
        }

        this.binding.rvCryptoFragmentCryptoList.adapter = this.cryptoListAdapter
        this.binding.rvCryptoFragmentCryptoList.layoutManager = layoutManager
    }

    private fun initViewModel() {
        Timber.d("initViewModel")
        this.viewModel.uiState.observe(viewLifecycleOwner, Observer(::render))
        this.viewModel.loadData()
    }


    private fun render(uiState: CryptoListUIState) {
        Timber.d("render: $uiState")
        when (uiState) {
            CryptoListUIState.ShowDefaultView -> {
            }
            CryptoListUIState.ShowLoadingView -> {
                binding.loadingView.container.visibility = View.VISIBLE
                binding.lyDataContainer.visibility = View.GONE
            }
            is CryptoListUIState.ShowErrorRetryView -> {
                Timber.d("render: on Error: ${uiState.t}")
                binding.loadingView.container.visibility = View.GONE

                binding.lyDataContainer.visibility = View.GONE
                binding.lyErrorRetryContainer.container.visibility = View.VISIBLE

                binding.lyErrorRetryContainer.btErrorRetryViewGenericRetry.setOnClickListener {
                    Timber.d("render: onClickListener")
                    viewModel.loadData()
                }
            }
            is CryptoListUIState.ShowDataView -> {
                binding.loadingView.container.visibility = View.GONE

                binding.lyDataContainer.visibility = View.VISIBLE
                binding.lyErrorRetryContainer.container.visibility = View.GONE

                this.cryptoListAdapter.setData(uiState.data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume: ")
    }
}