package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.FragmentLaunchAddressBinding
import com.smparkworld.daangnmarket.extension.getLastLocation
import com.smparkworld.daangnmarket.extension.showRequestPermissionDialog
import com.smparkworld.daangnmarket.extension.showSnackbar
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentLaunchAddressBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = loginViewModel

            initObserver()
            initAddressList(addressList)
        }.root
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        var isDenied = false
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) isDenied = true
        }
        if (!isDenied) {
            loadAroundAddress()
        } else {
            showRequestPermissionDialog()
        }
    }

    private fun loadAroundAddress() = getLastLocation { location ->
        loginViewModel.loadAroundAddress(location)
    }

    private fun initObserver() {
        loginViewModel.error.observe(viewLifecycleOwner) { showSnackbar(it) }
    }

    private fun initAddressList(list: RecyclerView) {
        val adapter = AddressAdapter()
        list.adapter = adapter

        loginViewModel.addressFlow.observe(viewLifecycleOwner) { flow ->
            viewLifecycleOwner.lifecycleScope.launch {
                flow.collectLatest { adapter.submitData(it) }
            }
        }
        loadAroundAddress()
    }
}