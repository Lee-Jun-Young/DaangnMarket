package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.FragmentLaunchAddressBinding
import com.smparkworld.daangnmarket.extension.getLastLocation
import com.smparkworld.daangnmarket.extension.showRequestPermissionDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddressFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by activityViewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAroundAddress()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentLaunchAddressBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        vm = loginViewModel

        btnAddressAround.setOnClickListener { loadAroundAddress() }
        initObserver()
        initAddressList(addressList)
    }.root

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                showRequestPermissionDialog()
                return
            }
        }
        loadAroundAddress()
    }

    private fun loadAroundAddress() {
        getLastLocation { loginViewModel.loadAroundAddress(it) }
    }

    private fun initObserver() {
        loginViewModel.addressSearch.observe(viewLifecycleOwner) {
            loginViewModel.searchAddress()
        }
    }

    private fun initAddressList(list: RecyclerView) {

        loginViewModel.addressFlow.observe(viewLifecycleOwner) { flow ->

            viewLifecycleOwner.lifecycleScope.launch {
                val adapter = AddressAdapter {
                    loginViewModel.setSelectedAddress(it)
                }
                list.adapter = adapter

                adapter.addLoadStateListener {
                    if (it.refresh is LoadState.NotLoading && adapter.itemCount == 0) {
                        loginViewModel.setAddressLoadState(null)
                    } else {
                        loginViewModel.setAddressLoadState(it)
                    }
                }
                flow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}